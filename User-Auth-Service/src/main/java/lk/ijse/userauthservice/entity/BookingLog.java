/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookingLog {
    @Id
    private String BookingId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String vehicleId;
    private String parkingSpaceId;

    private LocalDateTime reservedAt;
    private LocalDateTime releasedAt;
}
