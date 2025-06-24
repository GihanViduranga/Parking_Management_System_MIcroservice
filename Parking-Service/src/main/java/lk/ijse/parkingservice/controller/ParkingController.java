/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.parkingservice.controller;

import lk.ijse.parkingservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingservice.dto.ResponseDTO;
import lk.ijse.parkingservice.service.ParkingService;
import lk.ijse.parkingservice.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parkingSpace")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/createSpace")
    public ResponseEntity<ResponseDTO> createSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        try {
            parkingService.createSpace(parkingSpaceDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Parking Spot Created Successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/allSpaces")
    public ResponseEntity<ResponseDTO> getAllSpaces() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Success", parkingService.getAllSpaces()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PostMapping("/setAvailableParkingSpace")
    public ResponseEntity<ResponseDTO> setAvailableParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        try {
            System.out.println("Controller : " + parkingSpaceDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Success", parkingService.setAvailableParkingSpace(parkingSpaceDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PostMapping("/checkSpotNumber")
    public ResponseEntity<ResponseDTO> getSpotNumber(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Success", parkingService.checkSpotNumber(parkingSpaceDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
