package com.dailycode.ProductService.service;

import com.dailycode.ProductService.entity.Product;
import com.dailycode.ProductService.exception.ProductServiceCustomException;
import com.dailycode.ProductService.model.ProductRequest;
import com.dailycode.ProductService.model.ProductResponse;
import com.dailycode.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository pRepository;
    @Override
    public long addProduct(ProductRequest pRequest) {
        log.info("Adding product");

        Product product = Product.builder()
                .procductName(pRequest.getProductName())
                .price(pRequest.getPrice())
                .quantity(pRequest.getQuantity())
                .build();
        pRepository.save(product);
        log.info("product added");

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        Product product = pRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("product with the given id is not present", "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceOrder(long productId, long quantity) {
       log.info("Reduce order quantity {} for id:{}", quantity, productId);

       Product product = pRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException("product with the given id is not found", "PRODUCT_NOT_FOUND_EXCEPTION"));
       if(product.getQuantity()<quantity)
           throw new ProductServiceCustomException("Product out of stock ", "INSUFFICIENT_QUANTITY");
       product.setQuantity(product.getQuantity()-quantity);
       pRepository.save(product);
       log.info("product with id {} is updated ", productId);

    }

//    @Override
//    public List<ProductResponse> getAllProducts() {
//        List<ProductResponse> productResponses = pRepository.findAll().stream().map(productResponse ->BeanUtils.copyProperties(product, productResponse));
//        return products;
//    }
}
