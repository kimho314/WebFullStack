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
public class OrderEventConsumer {

    @KafkaListener(
            topics = {"${kafka.topics.orders}"},
            groupId = "order-processing-group",
            concurrency = "3",
            containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void processOrder(
            @Payload OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
            @Header(KafkaHeaders.OFFSET) Long offset
    ) {
        log.info("Received order event: {}", orderEvent);
        try {
            processingLogic();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void processingLogic() {
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
