package com.hutech.demo.mapper;

import com.hutech.demo.model.Booking;
import com.hutech.demo.model.Customer;
import com.hutech.demo.model.Helper;
import com.hutech.demo.model.Service;
import com.hutech.demo.model.Transaction;
import com.hutech.demo.response.BookingResponse;
import com.hutech.demo.response.CustomerResponse;
import com.hutech.demo.response.HelperResponse;
import com.hutech.demo.response.ServiceResponse;
import com.hutech.demo.response.TransactionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionResponse toResponse(Transaction Transaction) {
        if ( Transaction == null ) {
            return null;
        }

        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setBooking( bookingToBookingResponse( Transaction.getBooking() ) );
        transactionResponse.setId( Transaction.getId() );
        transactionResponse.setTransactionId( Transaction.getTransactionId() );
        transactionResponse.setOrderInfo( Transaction.getOrderInfo() );
        transactionResponse.setAmount( Transaction.getAmount() );
        transactionResponse.setResponseCode( Transaction.getResponseCode() );
        transactionResponse.setBankCode( Transaction.getBankCode() );
        transactionResponse.setTransactionStatus( Transaction.getTransactionStatus() );
        transactionResponse.setPayDate( Transaction.getPayDate() );
        transactionResponse.setCreatedAt( Transaction.getCreatedAt() );

        return transactionResponse;
    }

    @Override
    public List<TransactionResponse> getList(List<Transaction> Transactions) {
        if ( Transactions == null ) {
            return null;
        }

        List<TransactionResponse> list = new ArrayList<TransactionResponse>( Transactions.size() );
        for ( Transaction transaction : Transactions ) {
            list.add( toResponse( transaction ) );
        }

        return list;
    }

    protected CustomerResponse customerToCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId( customer.getId() );
        customerResponse.setUser( customer.getUser() );
        customerResponse.setAddress( customer.getAddress() );
        customerResponse.setPreferences( customer.getPreferences() );
        customerResponse.setCreatedAt( customer.getCreatedAt() );
        customerResponse.setUpdatedAt( customer.getUpdatedAt() );

        return customerResponse;
    }

    protected HelperResponse helperToHelperResponse(Helper helper) {
        if ( helper == null ) {
            return null;
        }

        HelperResponse helperResponse = new HelperResponse();

        helperResponse.setId( helper.getId() );
        helperResponse.setUser( helper.getUser() );
        helperResponse.setExperience( helper.getExperience() );
        helperResponse.setSkills( helper.getSkills() );
        helperResponse.setAvailability( helper.getAvailability() );
        helperResponse.setRatePerHour( helper.getRatePerHour() );
        helperResponse.setProfilePicture( helper.getProfilePicture() );
        helperResponse.setRating( helper.getRating() );
        helperResponse.setCreatedAt( helper.getCreatedAt() );
        helperResponse.setUpdatedAt( helper.getUpdatedAt() );

        return helperResponse;
    }

    protected ServiceResponse serviceToServiceResponse(Service service) {
        if ( service == null ) {
            return null;
        }

        ServiceResponse serviceResponse = new ServiceResponse();

        serviceResponse.setId( service.getId() );
        serviceResponse.setName( service.getName() );
        serviceResponse.setDescription( service.getDescription() );
        serviceResponse.setCreatedAt( service.getCreatedAt() );
        serviceResponse.setUpdatedAt( service.getUpdatedAt() );

        return serviceResponse;
    }

    protected BookingResponse bookingToBookingResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setId( booking.getId() );
        bookingResponse.setCustomer( customerToCustomerResponse( booking.getCustomer() ) );
        bookingResponse.setHelper( helperToHelperResponse( booking.getHelper() ) );
        bookingResponse.setService( serviceToServiceResponse( booking.getService() ) );
        bookingResponse.setStartTime( booking.getStartTime() );
        bookingResponse.setEndTime( booking.getEndTime() );
        bookingResponse.setStatus( booking.getStatus() );
        bookingResponse.setTotalAmount( booking.getTotalAmount() );
        bookingResponse.setCreatedAt( booking.getCreatedAt() );
        bookingResponse.setUpdatedAt( booking.getUpdatedAt() );

        return bookingResponse;
    }
}
