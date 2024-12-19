package com.hutech.demo.service;

import com.hutech.demo.createrequest.CreateServiceRequest;
import com.hutech.demo.mapper.ServiceMapper;
import com.hutech.demo.model.Service;
import com.hutech.demo.repository.ServiceRepository;
import com.hutech.demo.response.ServiceResponse;
import com.hutech.demo.service.inf.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceService implements IServiceService {

    @Autowired
    private final ServiceRepository serviceRepository;
    @Autowired
    private final ServiceMapper serviceMapper;

    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public ServiceResponse createService(CreateServiceRequest request) {
        Service service = serviceMapper.toService(request);
        serviceRepository.save(service);
        return serviceMapper.toResponse(service);
    }

    // Thêm dịch vụ mới
    public Service saveService(Service service) {
        return serviceRepository.save(service);
    }

    // Cập nhật dịch vụ
    public Service updateService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.delete(serviceRepository.findById(id).get());
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        Service review = serviceRepository.findById(id).get();
        if (review != null)
            return serviceMapper.toResponse(review);
        return null;
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        return serviceMapper.getList(serviceRepository.findAll());
    }
}
