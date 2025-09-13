package com.eazybytes.hellokafka.basic;

import com.eazybytes.hellokafka.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventPublisher {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String ordersTopic;

    public OrderEventPublisher(KafkaTemplate<String, OrderEvent> kafkaTemplate, @Value("${kafka.topic.orders}") String ordersTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.ordersTopic = ordersTopic;
    }

    public void publishOrderEvent(OrderEvent orderEvent) {
        try {
            kafkaTemplate.send(ordersTopic, orderEvent.orderId(), orderEvent)
                    .whenComplete((recordMetadata, ex) -> {
                        if (ex != null) {
                            log.error("Error when publishing order event", ex);
                        }
                        else {
                            log.info("Successfully published order event");
                        }
                    });
        }
        catch (Exception e) {
            log.error("Error publishing order event", e);
        }
    }
}
