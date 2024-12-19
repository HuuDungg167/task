package com.hutech.demo.filter;

import com.hutech.demo.model.Booking;
import com.hutech.demo.response.BookingResponse;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionFilter {
    private Long bookingId;

    private String transactionId;

    private String orderInfo;

    private BigDecimal amount;

    private String responseCode;

    private String bankCode;

    private String transactionStatus;

    private LocalDateTime payDate;

    private LocalDateTime createdAt;
}
