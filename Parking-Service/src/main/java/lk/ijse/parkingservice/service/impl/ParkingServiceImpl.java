/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.parkingservice.service.impl;

import lk.ijse.parkingservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingservice.dto.ResponseDTO;
import lk.ijse.parkingservice.entity.ParkingSpace;
import lk.ijse.parkingservice.repo.ParkingRepo;
import lk.ijse.parkingservice.service.ParkingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepo parkingRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean createSpace(ParkingSpaceDTO parkingSpaceDTO) {
        if (parkingRepo.existsById(parkingSpaceDTO.getId())) {
            return false;
        }

        String email = parkingSpaceDTO.getUserEmail();
        boolean isValidEmail = checkUserEmail(email);

        if (!isValidEmail) {
            return false;
        }
        parkingRepo.save(modelMapper.map(parkingSpaceDTO, ParkingSpace.class));
        return true;
    }

    public boolean checkUserEmail(String email) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8082/user-auth-service/api/v1/user/checkUserEmail/" + email;

            ResponseEntity<ResponseDTO> response = restTemplate.getForEntity(url, ResponseDTO.class);
            ResponseDTO responseBody = response.getBody();

            return responseBody != null && Boolean.TRUE.equals(responseBody.getData());

        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("User not found for email: " + email);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
