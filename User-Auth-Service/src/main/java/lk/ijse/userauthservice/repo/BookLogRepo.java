/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.repo;

import lk.ijse.userauthservice.entity.BookingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Optional;

@Repository
public interface BookLogRepo extends JpaRepository<BookingLog, String> {

//    Instant findReleasedTime(LocalDateTime releasedAt);
//
//    @Query("SELECT b.reservedAt FROM BookingLog b WHERE b.reservedAt = :reservedAt")
//    LocalDateTime findReservedTime(@Param("reservedAt") LocalDateTime reservedAt);
//
//    Optional<Object> findFirstByReservedAt(LocalDateTime reservedAt);

    boolean existsByReservedAtAndParkingSpaceId(LocalDateTime reservedAt, int parkingSpaceId);
}
