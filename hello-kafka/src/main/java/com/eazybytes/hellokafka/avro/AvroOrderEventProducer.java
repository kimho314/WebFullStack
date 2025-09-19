package com.eazybytes.hellokafka.avro;

import io.swagger.v3.oas.annotations.servers.Server;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.core.KafkaTemplate;

@Server
public class AvroOrderEventProducer {
    private final KafkaTemplate<String, GenericRecord> avroKafkaTemplate;
    private final SchemaManager schemaManager;
}
