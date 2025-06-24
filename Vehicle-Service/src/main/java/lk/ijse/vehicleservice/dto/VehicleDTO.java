/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.vehicleservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    int id;
    String userEmail;
    String plateNumber;
    String model;
    boolean isParked;
}


