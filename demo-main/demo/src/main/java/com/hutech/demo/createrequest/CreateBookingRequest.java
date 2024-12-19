package com.hutech.demo.createrequest;

import com.google.firebase.database.annotations.NotNull;
import com.hutech.demo.model.Booking;
import com.hutech.demo.model.Customer;
import com.hutech.demo.model.Helper;
import com.hutech.demo.model.Service;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {

    @NotNull("Customer ID is required")
    private Long customerId;

    @NotNull("Helper ID is required")
    private Long helperId;

    @NotNull("Service ID is required")
    private Long serviceId;

    @NotNull("Start time is required")
    @FutureOrPresent(message = "Start time must be in the future or present")
    private LocalDateTime startTime;

    @NotNull("End time is required")
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;
}
