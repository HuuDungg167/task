package com.hutech.demo.createrequest;

import com.hutech.demo.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUserRequest {
    private String username;

    private String password;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @Length(min = 10, max = 10, message = "Phone must be 10 characters")
    @Pattern(regexp = "^[0-9]*$", message = "Phone must be number")
    private String phone;

    private String provider;

    private String address;

    private User.Gender gender;

    private String avatarUrl;
}
