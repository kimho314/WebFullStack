package com.eazybytes.hellokafka.model;

public record OrderCountComparisonStats(
        PeriodStats currentPeriod,
        PeriodStats previousPeriod,
        Long changeCount,
        Double changePercentage,
        Boolean isIncreasing
) {
}
