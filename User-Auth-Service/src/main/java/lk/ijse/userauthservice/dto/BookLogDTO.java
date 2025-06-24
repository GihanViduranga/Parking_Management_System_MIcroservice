/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.dto;


import lk.ijse.userauthservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookLogDTO {
    private int BookingId;
    private String vehicleId;
    private int parkingSpaceId;
    private String userEmail;
    private LocalDateTime reservedAt;
    private LocalDateTime releasedAt;
}
