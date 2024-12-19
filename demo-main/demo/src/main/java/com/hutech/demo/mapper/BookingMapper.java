package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateBookingRequest;
import com.hutech.demo.model.Booking;
import com.hutech.demo.response.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {HelperMapper.class, CustomerMapper.class, ServiceMapper.class})
public interface BookingMapper {
    Booking toBooking(CreateBookingRequest request);
    @Mapping(target = "helper", source = "helper")
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "service", source = "service")
    BookingResponse toResponse(Booking booking);
    List<BookingResponse> getList(List<Booking> bookings);
}
