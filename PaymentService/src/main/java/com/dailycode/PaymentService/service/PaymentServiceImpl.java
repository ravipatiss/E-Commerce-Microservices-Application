package com.dailycode.PaymentService.service;

import com.dailycode.PaymentService.entity.Transaction;
import com.dailycode.PaymentService.model.PaymentMode;
import com.dailycode.PaymentService.model.PaymentRequest;
import com.dailycode.PaymentService.model.PaymentResponse;
import com.dailycode.PaymentService.repository.TransactionRepository;
import jakarta.ws.rs.SeBootstrap;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public long doPayment(PaymentRequest paymentRequest) {
       log.info("Recording Payment Details :{}", paymentRequest);
        Transaction transaction = Transaction.builder()
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("Success")
                .amount(paymentRequest.getAmount())
                .orderId(paymentRequest.getOrderId())
//                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentDate(Instant.now())
                .build();

        transactionRepository.save(transaction);
        log.info("Transaction completed with id :{} ", transaction.getId());

        return transaction.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsById(long orderId) {
        log.info("Transaction details");
        Transaction transaction= transactionRepository.findByOrderId(Long.valueOf(orderId));
        PaymentResponse paymentResponse = PaymentResponse.builder().paymentId(transaction.getId()).paymentMode(PaymentMode.valueOf(transaction.getPaymentMode())).paymentDate(transaction.getPaymentDate())
                .status(transaction.getPaymentStatus()).orderId(transaction.getOrderId()).amount(transaction.getAmount()).build();
        return paymentResponse;
    }
}
