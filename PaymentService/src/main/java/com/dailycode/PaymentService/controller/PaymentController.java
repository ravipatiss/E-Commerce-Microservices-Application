package com.dailycode.PaymentService.controller;

import com.dailycode.PaymentService.model.PaymentRequest;
import com.dailycode.PaymentService.model.PaymentResponse;
import com.dailycode.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsById(@PathVariable("id") long orderId){
        return new ResponseEntity<>(paymentService.getPaymentDetailsById(orderId), HttpStatus.OK);
    }
}
