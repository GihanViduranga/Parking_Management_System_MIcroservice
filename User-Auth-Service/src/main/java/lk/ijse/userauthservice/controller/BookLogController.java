/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.controller;

import lk.ijse.userauthservice.dto.BookLogDTO;
import lk.ijse.userauthservice.dto.ResponseDTO;
import lk.ijse.userauthservice.service.BookLogService;
import lk.ijse.userauthservice.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookLog")
public class BookLogController {

    @Autowired
    private BookLogService bookLogService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBookLog(@RequestBody BookLogDTO bookLogDTO) {
        try {
            bookLogService.addBookLog(bookLogDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Book Log Added Successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));

        }
    }
}
