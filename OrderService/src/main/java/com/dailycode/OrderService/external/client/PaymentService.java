package com.dailycode.OrderService.external.client;

import com.dailycode.OrderService.external.client.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentService {
    @RequestMapping(method = RequestMethod.POST, value = "/payment")
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);
}
