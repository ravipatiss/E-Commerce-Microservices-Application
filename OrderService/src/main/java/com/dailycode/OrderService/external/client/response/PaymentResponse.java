package com.dailycode.OrderService.external.client.response;

import com.dailycode.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
private long paymentId;
private Instant paymentDate;
private PaymentMode paymentMode;
private long orderId;
private long amount;
private String status;
}
