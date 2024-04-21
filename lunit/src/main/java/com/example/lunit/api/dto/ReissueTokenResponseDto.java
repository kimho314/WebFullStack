package com.example.lunit.api.dto;

import com.example.lunit.common.enums.TokenType;

public record ReissueTokenResponseDto(
        String token,
        TokenType tokenType
) {
}
