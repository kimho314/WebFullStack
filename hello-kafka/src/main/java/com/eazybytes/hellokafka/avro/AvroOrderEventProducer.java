package com.eazybytes.hellokafka.avro;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Server
public class AvroOrderEventProducer {
    private final KafkaTemplate<String, GenericRecord> avroKafkaTemplate;
    private final SchemaManager schemaManager;

    public AvroOrderEventProducer(KafkaTemplate<String, GenericRecord> avroKafkaTemplate, SchemaManager schemaManager) {
        this.avroKafkaTemplate = avroKafkaTemplate;
        this.schemaManager = schemaManager;
    }

    public void publishOrderEvent(
            String orderId,
            String customerId,
            Integer quantity,
            BigDecimal price
    ) {
        Schema schema = schemaManager.orderEventSchema();
    }

    private GenericRecord createAvroRecord(
            String orderId,
            String customerId,
            Integer quantity,
            BigDecimal price,
            Schema schema
    ) {
        GenericData.Record record = new GenericData.Record(schema);
        long now = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        record.put("orderId", orderId);
        record.put("customerId", customerId);
        record.put("quantity", quantity);
        record.put("price", convertPriceToBytes(price));
        record.put("status", new GenericData.EnumSymbol(schema.getField("status").schema(), "PENDING"));
        record.put("createdAt", now);
        record.put("updatedAt", now);
        record.put("version", 1L);

        return record;
    }

    private ByteBuffer convertPriceToBytes(BigDecimal price) {
        BigDecimal scaled = price.setScale(2);
        BigInteger unscaledValue = scaled.unscaledValue();
        return ByteBuffer.wrap(unscaledValue.toByteArray());
    }
}
