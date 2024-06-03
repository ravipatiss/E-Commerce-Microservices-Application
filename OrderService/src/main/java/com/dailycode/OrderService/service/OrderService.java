package com.dailycode.OrderService.service;

import com.dailycode.OrderService.model.OrderRequest;
import com.dailycode.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrder(long orderId);
}
