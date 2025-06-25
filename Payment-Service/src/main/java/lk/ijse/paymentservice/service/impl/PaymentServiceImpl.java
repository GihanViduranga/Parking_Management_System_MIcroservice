/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.paymentservice.service.impl;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.dto.ResponseDTO;
import lk.ijse.paymentservice.entity.Payment;
import lk.ijse.paymentservice.repo.PaymentRepo;
import lk.ijse.paymentservice.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
        int bookingId = paymentDTO.getBookingId();
        boolean isValidBookingId = checkBookingId(bookingId);

        if (!isValidBookingId) {
            return false;
        }
        paymentRepo.save(modelMapper.map(paymentDTO, Payment.class));
        return true;
    }

    private boolean checkBookingId(int bookingId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8082/user-auth-service/api/v1/bookLog/getBookingId/" + bookingId;

            ResponseEntity<ResponseDTO> response = restTemplate.getForEntity(url, ResponseDTO.class);
            ResponseDTO responseBody = response.getBody();

            if (responseBody != null && Boolean.TRUE.equals(responseBody.getData())) {
                return true;
            } else {
                throw new IllegalArgumentException("Booking ID not found in user-auth-service");
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Booking ID not found: " + bookingId);
        } catch (Exception e) {
            throw new RuntimeException("Error while validating booking ID: " + bookingId, e);
        }
    }
}
