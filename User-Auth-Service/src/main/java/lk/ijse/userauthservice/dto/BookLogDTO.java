/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookLogDTO {
    private String BookingId;
    private String vehicleId;
    private String parkingSpaceId;
    private LocalDateTime reservedAt;
    private LocalDateTime releasedAt;
}
