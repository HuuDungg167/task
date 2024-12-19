package com.hutech.demo.mapper;

import com.hutech.demo.model.Customer;
import com.hutech.demo.response.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CustomerMapper {
//    Customer toCustomer(CreateCustomerRequest request);

    CustomerResponse toResponse(Customer Customer);
    List<CustomerResponse> getList(List<Customer> Customers);
}
