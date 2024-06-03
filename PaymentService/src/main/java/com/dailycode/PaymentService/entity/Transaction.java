package com.dailycode.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Transaction_Details")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "Amount")
    private long amount;
    @Column(name = "Order_ID")
    private long orderId;
    @Column(name = "Payment_Mode")
    private String paymentMode;
    @Column(name = "Reference_number")
    private String referenceNumber;
    @Column(name = "Payment_date")
    private Instant paymentDate;
    @Column(name = "Payment_Status")
    private String paymentStatus;
}
