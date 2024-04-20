package com.example.lunit.api.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull String userName,
        @NotNull String password
) {
}
