package com.hutech.demo.mapper;

import com.hutech.demo.model.Customer;
import com.hutech.demo.response.CustomerResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerResponse toResponse(Customer Customer) {
        if ( Customer == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId( Customer.getId() );
        customerResponse.setUser( Customer.getUser() );
        customerResponse.setAddress( Customer.getAddress() );
        customerResponse.setPreferences( Customer.getPreferences() );
        customerResponse.setCreatedAt( Customer.getCreatedAt() );
        customerResponse.setUpdatedAt( Customer.getUpdatedAt() );

        return customerResponse;
    }

    @Override
    public List<CustomerResponse> getList(List<Customer> Customers) {
        if ( Customers == null ) {
            return null;
        }

        List<CustomerResponse> list = new ArrayList<CustomerResponse>( Customers.size() );
        for ( Customer customer : Customers ) {
            list.add( toResponse( customer ) );
        }

        return list;
    }
}
