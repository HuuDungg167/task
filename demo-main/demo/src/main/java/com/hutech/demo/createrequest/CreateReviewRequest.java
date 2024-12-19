package com.hutech.demo.createrequest;

import com.hutech.demo.model.Booking;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CreateReviewRequest {
    private Long bookingId;

    private Integer rating;

    private String comment;
}
