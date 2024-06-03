package com.dailycode.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForwardController {
    @GetMapping(path = "/orderServiceFallBack")
    public String orderServiceFallBack(){
        return "Order Service is down!";
    }
@GetMapping(path = "/productServiceFallBack")
    public String productServiceFallBack(){
        return "Product Service is down!";
    }
@GetMapping(path = "/paymentServiceFallBack")
    public String paymentServiceFallBack(){
        return "Payment Service is down!";
    }
}
