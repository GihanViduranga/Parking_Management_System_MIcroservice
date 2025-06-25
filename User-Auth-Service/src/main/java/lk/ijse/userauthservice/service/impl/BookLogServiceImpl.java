/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.service.impl;

import lk.ijse.userauthservice.dto.BookLogDTO;
import lk.ijse.userauthservice.dto.UserDto;
import lk.ijse.userauthservice.entity.BookingLog;
import lk.ijse.userauthservice.entity.User;
import lk.ijse.userauthservice.repo.BookLogRepo;
import lk.ijse.userauthservice.repo.UserRepository;
import lk.ijse.userauthservice.service.BookLogService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

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
            boolean isAlreadyBooked = bookLogRepo.existsByReservedAtAndParkingSpaceId(
                    bookLogDTO.getReservedAt(), bookLogDTO.getParkingSpaceId());

            if (isAlreadyBooked) {
                throw new Exception("Parking Space Already Booked at this time");
            }

            BookingLog bookingLog = modelMapper.map(bookLogDTO, BookingLog.class);

            User user = userRepository.findByEmail(bookLogDTO.getUserEmail())
                    .orElseThrow(() -> new Exception("User not found"));

            bookingLog.setUser(user);
            bookLogRepo.save(bookingLog);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookLogDTO> getAllBookings() {
        return modelMapper.map(bookLogRepo.findAll(),
                new TypeToken<List<BookLogDTO>>() {}.getType());
    }

    @Override
    public boolean getBookingId(int bookingId) {
        boolean isBookingIdExists = bookLogRepo.existsById(String.valueOf(bookingId));
        if (isBookingIdExists) {
            return true;
        } else {
            return false;
        }
    }
}
