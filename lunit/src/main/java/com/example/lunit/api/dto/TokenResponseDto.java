package com.example.lunit.api.dto;

import com.example.lunit.common.enums.ResultStatus;

public record TokenResponseDto(
        ResultStatus status,
        Integer statusCode,
        String accessToken,
        String refreshToken
) {
}
