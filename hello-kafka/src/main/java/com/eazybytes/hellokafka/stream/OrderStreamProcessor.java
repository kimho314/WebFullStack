package com.eazybytes.hellokafka.stream;

import com.eazybytes.hellokafka.model.FraudAlert;
import com.eazybytes.hellokafka.model.OrderEvent;
import com.eazybytes.hellokafka.model.WindowedOrderCount;
import com.eazybytes.hellokafka.model.WindowedSalesData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class OrderStreamProcessor {
    private final String orderTopic;
    private final String highValueOrdersTopic;
    private final String fraudAlertsTopic;

    private JsonSerde<OrderEvent> orderEventSerde;
    private JsonSerde<FraudAlert> fraudAlertSerde;
    private JsonSerde<WindowedOrderCount> windowedOrderCountSerde;
    private JsonSerde<WindowedSalesData> windowedSalesDataSerde;

    public OrderStreamProcessor(@Value("${kafka.topics.orders}") String orderTopic,
                                @Value("${kafka.topics.high-value-orders}") String highValueOrdersTopic,
                                @Value("${kafka.topics.fraud-alerts}") String fraudAlertsTopic) {
        this.orderTopic = orderTopic;
        this.highValueOrdersTopic = highValueOrdersTopic;
        this.fraudAlertsTopic = fraudAlertsTopic;

        this.orderEventSerde = createJsonSerde(OrderEvent.class);
        this.fraudAlertSerde = createJsonSerde(FraudAlert.class);
        this.windowedOrderCountSerde = createJsonSerde(WindowedOrderCount.class);
        this.windowedSalesDataSerde = createJsonSerde(WindowedSalesData.class);
    }

    private <T> JsonSerde<T> createJsonSerde(Class<T> clazz) {
        JsonSerde<T> jsonSerde = new JsonSerde<>();
        Map<String, Object> config = new HashMap<>();
        config.put("spring.json.trusted.packages", "org.kafka_lecture.model");
        config.put("spring.json.add.type.headers", false);
        config.put("spring.json.value.default.type", clazz.getName());

        jsonSerde.configure(config, false);
        return jsonSerde;
    }

}
