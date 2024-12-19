package com.hutech.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hutech.demo.model.Booking;
import com.hutech.demo.model.Customer;
import com.hutech.demo.model.Helper;
import com.hutech.demo.model.Service;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingResponse {
    private Long id;
    @JsonIgnore
    private CustomerResponse customer;
    @JsonIgnore
    private HelperResponse helper;

    private ServiceResponse service;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Booking.Status status;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
