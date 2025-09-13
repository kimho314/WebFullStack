package com.eazybytes.hellokafka.basic;

import com.eazybytes.hellokafka.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class OrderNotificationConsumer {

    @KafkaListener(
            topics = {"${kafka.topics.orders}"},
            groupId = "order-notificationi-group",
            concurrency = "1",
            containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void sendNotification(
            @Payload OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition
    ) {
        log.info("Sending notifications for order {} from partition {}", orderEvent.orderId(), partition);

        try {
            if (isHighValueOrder(orderEvent)) {
                sendHighValueOrderSms(orderEvent);
            }
        }
        catch (Exception e) {
            log.error("Failed to send notifications for order {}: {}", orderEvent.orderId(), e.getMessage());
        }
    }

    private void sendHighValueOrderSms(OrderEvent orderEvent) {
        log.info("SMS sent for high value order {}", orderEvent.orderId());
    }

    private boolean isHighValueOrder(OrderEvent orderEvent) {
        return orderEvent.price().compareTo(BigDecimal.valueOf(1000)) >= 0;
    }
}
