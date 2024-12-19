package com.hutech.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "transaction_id", unique = true, nullable = false, length = 100)
    private String transactionId; // VNPay's unique transaction ID

    @Column(name = "order_info", columnDefinition = "TEXT")
    private String orderInfo; // Description of the transaction (e.g., what it was for)

    @Column(name = "amount", nullable = false)
    private BigDecimal amount; // Transaction amount

    @Column(name = "response_code", length = 10)
    private String responseCode; // VNPay's response code for the transaction status

    @Column(name = "bank_code", length = 50)
    private String bankCode; // Bank involved in the transaction

    @Column(name = "transaction_status", length = 20)
    private String transactionStatus; // Status (e.g., "SUCCESS", "FAILED")

    @Column(name = "pay_date")
    private LocalDateTime payDate; // Payment date returned by VNPay

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
    }

}
