package com.hutech.demo.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;

    private BookingResponse booking;

    private String transactionId;

    private String orderInfo;

    private BigDecimal amount;

    private String responseCode;

    private String bankCode;

    private String transactionStatus;

    private LocalDateTime payDate;

    private LocalDateTime createdAt;
}
