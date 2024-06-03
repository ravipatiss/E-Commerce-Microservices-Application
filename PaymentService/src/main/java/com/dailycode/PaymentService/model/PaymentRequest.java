package com.dailycode.PaymentService.model;

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
