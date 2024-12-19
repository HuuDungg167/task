package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateBookingRequest;
import com.hutech.demo.createrequest.CreateUserRequest;
import com.hutech.demo.model.Booking;
import com.hutech.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequest request);
}
