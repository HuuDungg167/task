package com.hutech.demo.response;

import com.hutech.demo.model.Helper;
import com.hutech.demo.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class HelperResponse {
    private Long id;

    private User user;

    private Integer experience;

    private String skills;

    private Helper.Availability availability;

    private BigDecimal ratePerHour;

    private String profilePicture;

    private BigDecimal rating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
