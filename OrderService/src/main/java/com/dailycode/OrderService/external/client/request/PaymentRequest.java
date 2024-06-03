package com.dailycode.OrderService.external.client.request;

import com.dailycode.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private long orderId;
    private long amount;
//    private String referenceNumber;
    private PaymentMode paymentMode;



}
