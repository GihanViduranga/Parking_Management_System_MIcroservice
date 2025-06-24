/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BookingId;
    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private User user;
    private String vehicleId;
    private int parkingSpaceId;

    private LocalDateTime reservedAt;
    private LocalDateTime releasedAt;
}
