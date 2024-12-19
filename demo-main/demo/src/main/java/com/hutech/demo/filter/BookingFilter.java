package com.hutech.demo.filter;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingFilter {
    private Long customerId;

    private Long helperId;

    private Long serviceId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status;
}
