/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.service.impl;

import lk.ijse.userauthservice.dto.BookLogDTO;
import lk.ijse.userauthservice.entity.BookingLog;
import lk.ijse.userauthservice.entity.User;
import lk.ijse.userauthservice.repo.BookLogRepo;
import lk.ijse.userauthservice.repo.UserRepository;
import lk.ijse.userauthservice.service.BookLogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookLogServiceImpl implements BookLogService {

    @Autowired
    private BookLogRepo bookLogRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addBookLog(BookLogDTO bookLogDTO) {
        try {
            if (bookLogRepo.existsById(bookLogDTO.getBookingId())) {
                throw new Exception("Book Log Already Exists");
            }
            BookingLog bookingLog = modelMapper.map(bookLogDTO, BookingLog.class);

            User user = userRepository.findById(bookLogDTO.getBookingId())
                    .orElseThrow(() -> new Exception("User not found with id: " + bookLogDTO.getBookingId()));

            bookingLog.setUser(user);
            bookLogRepo.save(bookingLog);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
