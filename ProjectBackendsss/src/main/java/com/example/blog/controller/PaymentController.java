package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.blog.entity.PaymentDetails;
import com.example.blog.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<String> savePaymentDetails(@RequestBody PaymentDetails paymentDetails) {
        String response = paymentService.savePaymentDetails(paymentDetails);
        return ResponseEntity.ok(response);
    }
}

