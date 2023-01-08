package com.example.simplekafkaproducer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Slf4j
@SpringBootApplication
public class SimpleKafkaProducerApplication {
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "127.0.0.1:9092";

    public static void main(String[] args) {
        var configs = new KafkaProperties();
        var stringObjectMap = configs.buildProducerProperties();
        stringObjectMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        stringObjectMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        stringObjectMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Object, String> kafkaProducer = new KafkaProducer<>(stringObjectMap);

        var messageValue = "testMessage";
        var producerRecord = new ProducerRecord<>(TOPIC_NAME, messageValue);

        kafkaProducer.send(producerRecord);
        log.info("{}", producerRecord);
        kafkaProducer.flush();
        kafkaProducer.close();
        SpringApplication.run(SimpleKafkaProducerApplication.class, args);
    }

}
