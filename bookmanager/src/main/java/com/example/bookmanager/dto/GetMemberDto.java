package com.example.bookmanager.dto;

import lombok.Builder;

@Builder
public record GetMemberDto(
        String userId,
        String name
) {
}
