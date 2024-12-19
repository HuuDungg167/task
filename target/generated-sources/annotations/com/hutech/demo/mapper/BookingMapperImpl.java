package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateBookingRequest;
import com.hutech.demo.model.Booking;
import com.hutech.demo.response.BookingResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Autowired
    private HelperMapper helperMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public Booking toBooking(CreateBookingRequest request) {
        if ( request == null ) {
            return null;
        }

        Booking.BookingBuilder booking = Booking.builder();

        booking.startTime( request.getStartTime() );
        booking.endTime( request.getEndTime() );

        return booking.build();
    }

    @Override
    public BookingResponse toResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setHelper( helperMapper.toResponse( booking.getHelper() ) );
        bookingResponse.setCustomer( customerMapper.toResponse( booking.getCustomer() ) );
        bookingResponse.setService( serviceMapper.toResponse( booking.getService() ) );
        bookingResponse.setId( booking.getId() );
        bookingResponse.setStartTime( booking.getStartTime() );
        bookingResponse.setEndTime( booking.getEndTime() );
        bookingResponse.setStatus( booking.getStatus() );
        bookingResponse.setTotalAmount( booking.getTotalAmount() );
        bookingResponse.setCreatedAt( booking.getCreatedAt() );
        bookingResponse.setUpdatedAt( booking.getUpdatedAt() );

        return bookingResponse;
    }

    @Override
    public List<BookingResponse> getList(List<Booking> bookings) {
        if ( bookings == null ) {
            return null;
        }

        List<BookingResponse> list = new ArrayList<BookingResponse>( bookings.size() );
        for ( Booking booking : bookings ) {
            list.add( toResponse( booking ) );
        }

        return list;
    }
}
