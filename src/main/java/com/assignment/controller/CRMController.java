package com.assignment.controller;

import com.assignment.model.ServiceRequest;
import com.assignment.service.CRMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm")
public class CRMController {
    @Autowired
    CRMService crmService;

    @RequestMapping(path = "/requests", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ServiceRequest>> getAllRequests() {
        return ResponseEntity.ok(crmService.getAllRequests());
    }

    @RequestMapping(path = "/request", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ServiceRequest> createRequest(@RequestBody ServiceRequest request) {
        ServiceRequest createdRequest = crmService.createRequest(request);
        crmService.forwardToBackOffice(createdRequest);
        return ResponseEntity.ok(createdRequest);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ServiceRequest> requestStatus(@PathVariable Long id) {
        return ResponseEntity.ok(crmService.requestStatus(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ServiceRequest> updateStatus(@PathVariable Long id, @RequestParam String status) {
        ServiceRequest createdRequest = crmService.updateStatus(id, status);
        crmService.forwardToBackOffice(createdRequest);
        return ResponseEntity.ok(createdRequest);
    }
}
