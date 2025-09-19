package com.eazybytes.hellokafka.avro;

import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaMetadata;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SchemaManager implements ApplicationRunner {
    private final String schemaRegistryUrl;
    private final SchemaRegistryClient client;
    private Schema cachedSchema;

    public SchemaManager(@Value("${schema.registry.url:http://localhost:8081}") String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
        this.client = new CachedSchemaRegistryClient(schemaRegistryUrl, 100);
    }

    public Schema orderEventSchema() {
        if (this.cachedSchema != null) {
            return this.cachedSchema;
        }
        else {
            Schema schema = loadSchemaFromRegistry();
            this.cachedSchema = schema;
            return schema;
        }
    }

    public Schema loadSchemaFromRegistry() {
        try {
            String subject = "orders-avro-value";
            SchemaMetadata last = client.getLatestSchemaMetadata(subject);
            String schemaString = last.getSchema();

            return new Schema.Parser().parse(schemaString);
        }
        catch (Exception e) {
            log.warn("Failed to load schema from register", e);
            return loadSchemaFromFile("avro/order-entity.avsc");
        }
    }

    public Schema loadSchemaFromFile(String path) {
        log.info("path {}", path);
        ClassPathResource resource = new ClassPathResource(path);
        String content;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            return new Schema.Parser().parse(content);
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to load schema from " + path);
        }
    }

    private @Nullable Integer registerSchemaIfNotExists() {
        // topic - name - key - value
        try {
            String subject = "orders-avro-value";
            List<Integer> existing;
            try {
                existing = client.getAllVersions(subject);
            }
            catch (Exception e) {
                log.info("subject does not exist");
                existing = List.of();
            }

            if (!existing.isEmpty()) {
                SchemaMetadata last = client.getLatestSchemaMetadata(subject);
                return last.getId();
            }

            Schema schema = loadSchemaFromFile("avro/order-entity.avsc");
            AvroSchema avroSchema = new AvroSchema(schema);
            int schemaId = client.register(subject, avroSchema);
            return schemaId;
        }
        catch (Exception e) {
            log.error("failed to register schema", e);
            return null;
        }


    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        registerSchemaIfNotExists();
    }
}
