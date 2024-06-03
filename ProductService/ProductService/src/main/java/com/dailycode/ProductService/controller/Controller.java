package com.dailycode.ProductService.controller;

import com.dailycode.ProductService.entity.Product;
import com.dailycode.ProductService.model.ProductRequest;
import com.dailycode.ProductService.model.ProductResponse;
import com.dailycode.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class Controller {

    @Autowired
    private ProductService pS;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest pRequest){
        long productId = pS.addProduct(pRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
        ProductResponse productResponse = pS.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @PutMapping(path = "reduceorder/{id}")
    public ResponseEntity<Void> reduceOrder(@PathVariable("id") long productId, @RequestParam long quantity) {

        pS.reduceOrder(productId, quantity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
//    @GetMapping
//    public ResponseEntity<List<ProductResponse>> getAllProducts(){
//        List<ProductResponse> products = pS.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }



