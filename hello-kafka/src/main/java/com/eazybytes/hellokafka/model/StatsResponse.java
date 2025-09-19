package com.eazybytes.hellokafka.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Objects;

public record StatsResponse<T>(
        Boolean success,
        @Nullable T data,
        String message,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp
) {
    public StatsResponse(Boolean success, T data, String message, @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp) {
        this.success = success;
        this.data = data;
        this.message = message;
        if (Objects.isNull(timestamp)) {
            this.timestamp = LocalDateTime.now();
        }
        else {
            this.timestamp = timestamp;
        }
    }
}
