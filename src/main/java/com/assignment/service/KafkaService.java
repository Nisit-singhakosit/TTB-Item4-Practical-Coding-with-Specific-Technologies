package com.assignment.service;

import org.springframework.stereotype.Service;

@Service
public interface KafkaService {
    void sendMessage(String topic, String message);
}
