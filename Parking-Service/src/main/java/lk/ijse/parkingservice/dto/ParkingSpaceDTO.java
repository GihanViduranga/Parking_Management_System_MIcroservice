/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.parkingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSpaceDTO {
    private int id;
    private int spotNumber;
    private String location;
    private boolean isAvailable;
    private String userEmail;
    private String zone;
}
