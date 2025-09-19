package com.eazybytes.hellokafka.avro;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class AvroOrderEventConsumer {
    @KafkaListener(
            topics = {"orders-avro"},
            groupId = "avro-order-processor-v1",
            containerFactory = "avroKafkaListenerContainerFactory"
    )
    public void handleOrderEventV1(
            @Payload GenericRecord avroRecord,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.OFFSET) Long offset
    ) {
        try {
            OrderDataV1 orderData = extractOrderDataFromAvro(avroRecord);
            log.info("Processing Avro order: orderId={}, partition={}, offset={}", orderData.orderId(), partition, offset);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private OrderDataV1 extractOrderDataFromAvro(GenericRecord record) {
        return new OrderDataV1(
                record.get("orderId").toString(),
                record.get("customerId").toString(),
                Integer.parseInt(record.get("quantity").toString()),
                convertBytesToPrice((ByteBuffer) record.get("price")),
                convertTimestamp(Long.valueOf(record.get("createdAt").toString())),
                record.get("status").toString(),
                Long.valueOf(record.get("version").toString())
        );
    }

    private BigDecimal convertBytesToPrice(ByteBuffer byteBuffer) {
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        BigInteger bigInt = new BigInteger(bytes);
        return new BigDecimal(bigInt, 2);
    }

    private LocalDateTime convertTimestamp(Long epochMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneOffset.UTC);
    }
}

record OrderDataV1(
        String orderId,
        String customerId,
        Integer quantity,
        BigDecimal price,
        LocalDateTime timestamp,
        String status,
        Long version
) {

}
