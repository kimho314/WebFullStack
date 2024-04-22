package com.example.lunit.common.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CommonResult<T>(
        Double predictionTime,
        String resultStatus,
        Integer statusCode,
        T prediction
) {
}
