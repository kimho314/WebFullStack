package com.example.lunit.api.dto;

import com.example.lunit.common.enums.ResultStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AnalyzeDicomResponseDto(
        Double predictionTime,
        ResultStatus status,
        Integer statusCode,
        PredictionDto prediction
) {
}
