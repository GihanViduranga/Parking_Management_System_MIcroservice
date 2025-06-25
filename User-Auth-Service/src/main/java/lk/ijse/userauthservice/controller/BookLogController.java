/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.controller;

import lk.ijse.userauthservice.dto.BookLogDTO;
import lk.ijse.userauthservice.dto.ResponseDTO;
import lk.ijse.userauthservice.service.BookLogService;
import lk.ijse.userauthservice.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookLog")
public class BookLogController {

    @Autowired
    private BookLogService bookLogService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBookLog(@RequestBody BookLogDTO bookLogDTO) {
        try {
            bookLogService.addBookLog(bookLogDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Successfully Booked a Parking Spot", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));

        }
    }

    @GetMapping("/allBookings")
    public ResponseEntity<ResponseDTO> getAllBookings() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Success", bookLogService.getAllBookings()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    /*@GetMapping("/getBookingId/{bookingId}")
    public ResponseEntity<ResponseDTO> getBookingId(@RequestBody int bookingId){
        try {
            boolean isBookingIdExists = bookLogService.getBookingId(bookingId);
            if (isBookingIdExists) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDTO(VarList.Found, "Booking Id exists", true));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(VarList.Not_Found, "Booking Id does not exist", false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }*/

    @GetMapping("/getBookingId/{bookingId}")
    public ResponseEntity<ResponseDTO> getBookingId(@PathVariable int bookingId) {
        try {
            boolean isBookingIdExists = bookLogService.getBookingId(bookingId);
            if (isBookingIdExists) {
                return ResponseEntity.ok(new ResponseDTO(VarList.Found, "Booking Id exists", true));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(VarList.Not_Found, "Booking Id does not exist", false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
