package com.eazybytes.hellokafka.model;

import java.time.LocalDateTime;

public record PeriodStats(
        LocalDateTime windowStart,
        LocalDateTime windowEnd,
        Long orderCount
) {
}
