package com.dailycode.PaymentService.service;

import com.dailycode.PaymentService.model.PaymentRequest;
import com.dailycode.PaymentService.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsById(long orderId);
}
