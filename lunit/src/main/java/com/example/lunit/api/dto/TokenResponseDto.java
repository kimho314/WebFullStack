package com.example.lunit.api.dto;

public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {
}
