package com.dailycode.OrderService.controller;

import com.dailycode.OrderService.model.OrderRequest;
import com.dailycode.OrderService.model.OrderResponse;
import com.dailycode.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService oS;
@PostMapping(path = "/placeorder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long id = oS.placeOrder(orderRequest);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") long orderId){
        OrderResponse orderResponse = oS.getOrder(orderId);


        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }


}
