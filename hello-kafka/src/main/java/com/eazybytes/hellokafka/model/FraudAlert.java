package com.eazybytes.hellokafka.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public record FraudAlert(
        String orderId,
        String customerId,
        String reason,
        FraudSeverity severity,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mmm:ss") LocalDateTime timestamp
) {

    public FraudAlert(String orderId, String customerId, String reason, FraudSeverity severity, @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mmm:ss") LocalDateTime timestamp) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.reason = reason;
        this.severity = severity;
        if (Objects.isNull(timestamp)) {
            this.timestamp = LocalDateTime.now();
        }
        else {
            this.timestamp = timestamp;
        }
    }
}
