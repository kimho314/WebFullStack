package com.eazybytes.hellokafka.basic;

import com.eazybytes.hellokafka.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderAnalyticsConsumer {
    @KafkaListener(
            topics = {"${kafka.topics.orders}"},
            groupId = "order-analytics-group",
            concurrency = "2",
            containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void collectAnalytics(
            @Payload OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition
    ) {
        log.info("Collecting analytics for order {} from partition {}", orderEvent.orderId(), partition);

        try {
            updateCustomerStatics(orderEvent);
        }
        catch (Exception e) {
            log.error("Failed to collect analytics for order {}: {}", orderEvent.orderId(), e.getMessage());
        }
    }

    private void updateCustomerStatics(OrderEvent orderEvent) {
        log.info("Updated customer statistics for {}", orderEvent.customerId());
    }
}
