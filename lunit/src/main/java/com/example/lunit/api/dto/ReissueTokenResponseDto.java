package com.example.lunit.api.dto;

import com.example.lunit.common.enums.ResultStatus;
import com.example.lunit.common.enums.TokenType;

public record ReissueTokenResponseDto(
        ResultStatus status,
        Integer statusCode,
        String token,
        TokenType tokenType
) {
}
