/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.parkingservice.service;

import lk.ijse.parkingservice.dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingService {
    boolean createSpace(ParkingSpaceDTO parkingSpaceDTO);

    List<ParkingSpaceDTO> getAllSpaces();

    ParkingSpaceDTO setAvailableParkingSpace(ParkingSpaceDTO parkingSpaceDTO);

    boolean checkSpotNumber(ParkingSpaceDTO parkingSpaceDTO);
}
