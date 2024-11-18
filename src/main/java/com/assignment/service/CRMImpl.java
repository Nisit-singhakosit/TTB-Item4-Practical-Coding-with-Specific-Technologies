package com.assignment.service;

import com.assignment.model.ServiceRequest;
import com.assignment.repository.ServiceRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Log4j2
public class CRMImpl implements CRMService {
    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Autowired
    KafkaService kafkaService;

    @Value("${kafka.topic.request}")
    private String requestTopic;

    @Value("${kafka.topic.update}")
    private String updateTopic;

    @Override
    public ServiceRequest createRequest(ServiceRequest request) {
        log.info("Create request: {}", request);
        forwardToBackOffice(requestTopic, serviceRequestRepository.save(request));
        return request;
    }

    @Override
    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    @Override
    public ServiceRequest updateStatus(Long id, String status) {
        log.info("Update status for id: {} to {}", id, status);
        ServiceRequest request = serviceRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        forwardToBackOffice(updateTopic, serviceRequestRepository.save(request));
        return request;
    }

    @Override
    public ServiceRequest requestStatus(Long id) {
        log.info("Request status for id: {}", id);
        return serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    private void forwardToBackOffice(String topic, ServiceRequest createdRequest) {
        try {
            kafkaService.sendMessage(topic, new ObjectMapper().writeValueAsString(createdRequest));
        } catch (JsonProcessingException e) {
            log.error("Error while sending message to Kafka", e);
        }
    }
}
