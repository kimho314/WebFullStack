package com.eazybytes.hellokafka.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record OrderEvent(
        String orderId,
        String customerId,
        Integer quantity,
        BigDecimal price,
        String eventType,
        String status,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp
) {
    public OrderEvent(String orderId,
                      String customerId,
                      Integer quantity,
                      BigDecimal price,
                      String eventType,
                      String status,
                      @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.price = price;
        if (!StringUtils.hasText(eventType)) {
            this.eventType = "ORDER_CREATED";
        }
        else {
            this.eventType = eventType;
        }
        if (!StringUtils.hasText(status)) {
            this.status = "PENDING";
        }
        else {
            this.status = status;
        }
        if (Objects.isNull(timestamp)) {
            this.timestamp = LocalDateTime.now();
        }
        else {
            this.timestamp = timestamp;
        }
    }
}
