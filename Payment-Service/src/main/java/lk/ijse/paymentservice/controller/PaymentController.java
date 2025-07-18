/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.paymentservice.controller;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.dto.ResponseDTO;
import lk.ijse.paymentservice.service.PaymentService;
import lk.ijse.paymentservice.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/paymentSaveAndGetReceipt")
    public ResponseEntity<PaymentDTO> paymentSave(@RequestBody PaymentDTO paymentDTO){
        try {
            PaymentDTO savedPayment = paymentService.savePayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
