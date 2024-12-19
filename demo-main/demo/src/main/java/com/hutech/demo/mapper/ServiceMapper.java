package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateServiceRequest;
import com.hutech.demo.model.Service;
import com.hutech.demo.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    Service toService(CreateServiceRequest request);

    ServiceResponse toResponse(Service Service);
    List<ServiceResponse> getList(List<Service> Services);
}
