package com.hutech.demo.response;

import com.hutech.demo.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponse {
    private Long id;

    private User user;

    private String address;

    private String preferences;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
