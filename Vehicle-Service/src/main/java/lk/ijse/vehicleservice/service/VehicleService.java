/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.vehicleservice.service;


import lk.ijse.vehicleservice.dto.VehicleDTO;

public interface VehicleService {

    boolean addVehicle(VehicleDTO vehicleDTO);

    VehicleDTO parkVehicle(VehicleDTO vehicleDTO);
}
