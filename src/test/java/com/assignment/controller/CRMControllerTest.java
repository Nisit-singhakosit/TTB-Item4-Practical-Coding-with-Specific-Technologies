package com.assignment.controller;

import com.assignment.model.ServiceRequest;
import com.assignment.service.CRMService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CRMControllerTest {
    @InjectMocks
    private CRMController crmController;

    @Mock
    private CRMService crmService;

    public CRMControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRequests() {
        // Arrange
        List<ServiceRequest> mockRequests = Arrays.asList(
                new ServiceRequest(1L, "Request1", "Pending"),
                new ServiceRequest(2L, "Request2", "Completed")
        );
        when(crmService.getAllRequests()).thenReturn(mockRequests);

        // Act
        ResponseEntity<List<ServiceRequest>> response = crmController.getAllRequests();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(crmService, times(1)).getAllRequests();
    }

    @Test
    public void testCreateRequest() {
        // Arrange
        ServiceRequest mockRequest = new ServiceRequest(null, "New Request", "Pending");
        ServiceRequest savedRequest = new ServiceRequest(1L, "New Request", "Pending");
        when(crmService.createRequest(mockRequest)).thenReturn(savedRequest);

        // Act
        ResponseEntity<ServiceRequest> response = crmController.createRequest(mockRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(crmService, times(1)).createRequest(mockRequest);
    }

    @Test
    public void testRequestStatus() {
        // Arrange
        ServiceRequest mockRequest = new ServiceRequest(1L, "Request1", "Pending");
        when(crmService.requestStatus(1L)).thenReturn(mockRequest);

        // Act
        ResponseEntity<ServiceRequest> response = crmController.requestStatus(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Request1", response.getBody().getRequestType());
        verify(crmService, times(1)).requestStatus(1L);
    }

    @Test
    public void testUpdateStatus() {
        // Arrange
        ServiceRequest updatedRequest = new ServiceRequest(1L, "Request1", "Completed");
        when(crmService.updateStatus(1L, "Completed")).thenReturn(updatedRequest);

        // Act
        ResponseEntity<ServiceRequest> response = crmController.updateStatus(1L, "Completed");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Completed", response.getBody().getStatus());
        verify(crmService, times(1)).updateStatus(1L, "Completed");
    }
}
