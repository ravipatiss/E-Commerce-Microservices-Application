package com.dailycode.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private long quantity;
    private long amount;
    private PaymentMode paymentMode;
    private long productId;

}
