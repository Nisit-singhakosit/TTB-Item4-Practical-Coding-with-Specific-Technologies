package com.assignment.service;

import com.assignment.model.ServiceRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CRMService {
    ServiceRequest createRequest(ServiceRequest request);
    List<ServiceRequest> getAllRequests();
    ServiceRequest updateStatus(Long id, String status);
    ServiceRequest requestStatus(Long id);
    void forwardToBackOffice(ServiceRequest createdRequest);
}
