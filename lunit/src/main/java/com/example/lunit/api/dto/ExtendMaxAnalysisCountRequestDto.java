package com.example.lunit.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ExtendMaxAnalysisCountRequestDto(
        @NotNull @Min(1) Integer extendCount
) {
}
