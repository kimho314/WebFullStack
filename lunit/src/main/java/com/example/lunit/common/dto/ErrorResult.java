package com.example.lunit.common.dto;

import com.example.lunit.common.enums.ResultStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ErrorResult(
        String status,
        Integer statusCode,
        String message
) {
    public ErrorResult(Integer statusCode, String message) {
        this(ResultStatus.FAIL.name(), statusCode, message);
    }
}
