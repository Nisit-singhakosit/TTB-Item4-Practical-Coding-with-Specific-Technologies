package com.assignment.service;

import com.assignment.model.ServiceRequest;
import com.assignment.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CRMServiceImpl implements CRMService {
    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Override
    public ServiceRequest createRequest(ServiceRequest request) {
        return serviceRequestRepository.save(request);
    }

    @Override
    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    @Override
    public ServiceRequest updateStatus(Long id, String status) {
        ServiceRequest request = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        return serviceRequestRepository.save(request);
    }

    @Override
    public ServiceRequest requestStatus(Long id) {
        return serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public void forwardToBackOffice(ServiceRequest createdRequest) {

    }
}
