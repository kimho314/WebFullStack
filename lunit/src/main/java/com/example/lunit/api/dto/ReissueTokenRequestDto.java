package com.example.lunit.api.dto;

import jakarta.validation.constraints.NotNull;

public record ReissueTokenRequestDto(
        @NotNull String userName,
        @NotNull String token
) {
}
