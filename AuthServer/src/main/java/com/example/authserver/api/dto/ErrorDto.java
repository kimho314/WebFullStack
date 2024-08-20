package com.example.authserver.api.dto;

import lombok.Builder;

@Builder
public record ErrorDto(int code, String message) {
}
