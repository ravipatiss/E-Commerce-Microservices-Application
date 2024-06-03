package com.dailycode.ProductService.service;

import com.dailycode.ProductService.entity.Product;
import com.dailycode.ProductService.model.ProductRequest;
import com.dailycode.ProductService.model.ProductResponse;

import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest pRequest);

    ProductResponse getProductById(long productId);

    void reduceOrder(long productId, long quantity);

//    List<ProductResponse> getAllProducts();
}
