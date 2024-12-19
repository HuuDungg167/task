package com.hutech.demo.controller;

import com.hutech.demo.createrequest.CreateServiceRequest;
import com.hutech.demo.response.ServiceResponse;
import com.hutech.demo.service.inf.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private IServiceService ServiceService;
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> createService(@RequestBody CreateServiceRequest request) {
        ServiceResponse newService = ServiceService.createService(request);
        return ResponseEntity.ok(newService);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable Long id) {
        ServiceResponse Service = ServiceService.getServiceById(id);
        return Service != null ? ResponseEntity.ok(Service) : ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public List<ServiceResponse> getAllServices() {
        return ServiceService.getAllServices();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        ServiceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
