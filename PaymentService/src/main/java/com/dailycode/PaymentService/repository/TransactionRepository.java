package com.dailycode.PaymentService.repository;

import com.dailycode.PaymentService.entity.Transaction;
import com.dailycode.PaymentService.model.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByOrderId(long orderId);
}
