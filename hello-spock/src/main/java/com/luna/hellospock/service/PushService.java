package com.luna.hellospock.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PushService {
    private final static String TOPIC_NAME = "push.notification";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PushService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notification(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}
