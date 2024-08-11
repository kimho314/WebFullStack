package com.example.authserver.api.base.dto;

import lombok.Builder;

@Builder
public record ErrorDto(int code, String message) {
}
