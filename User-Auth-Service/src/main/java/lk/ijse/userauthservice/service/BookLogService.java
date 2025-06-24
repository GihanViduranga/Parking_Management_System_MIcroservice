/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.service;

import lk.ijse.userauthservice.dto.BookLogDTO;

import java.util.List;

public interface BookLogService {


    void addBookLog(BookLogDTO bookLogDTO);

    List<BookLogDTO> getAllBookings();

}
