package com.hutech.demo.service.inf;

import com.hutech.demo.createrequest.CreateServiceRequest;
import com.hutech.demo.response.ServiceResponse;

import java.util.List;

public interface IServiceService {
    ServiceResponse createService(CreateServiceRequest request);
    //    ServiceResponse updateService(Long id, String status);
    void deleteService(Long id);
    ServiceResponse getServiceById(Long id);
    List<ServiceResponse> getAllServices();
}
