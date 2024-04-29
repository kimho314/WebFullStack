package com.example.lunit.api.dto;

import lombok.Builder;

@Builder
public record GetAnalyzeResultResponseDto(
        Double predictionTime,
        Double frontalScore,
        String createdAt
) {
}
