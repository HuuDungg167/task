package com.hutech.demo.service.imp;

import com.hutech.demo.model.Helper;
import com.hutech.demo.repository.HelperRepository;
import com.hutech.demo.response.HelperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelperService {
    @Autowired
    private HelperRepository helperRepository;

    public List<HelperResponse> getAvailableHelpers() {
        List<Helper> helpers = helperRepository.findAll();
        return helpers.stream()
                .map(helper -> new HelperResponse(/* Map các trường từ Helper sang HelperResponse */))
                .collect(Collectors.toList());
    }
}

