package com.dailycode.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//name should be same as the application name in productservice application
//and service name from the path in productservice controller
@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService {

//    @PutMapping(path = "reduceorder/{id}")
    @RequestMapping(method = RequestMethod.PUT, value = "/product/reduceorder/{id}")
    ResponseEntity<Void> reduceOrder(@PathVariable("id") long productId, @RequestParam long quantity);
}
