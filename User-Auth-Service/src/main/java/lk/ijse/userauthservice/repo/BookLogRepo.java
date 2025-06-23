/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.repo;

import lk.ijse.userauthservice.entity.BookingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLogRepo extends JpaRepository<BookingLog, String> {
}
