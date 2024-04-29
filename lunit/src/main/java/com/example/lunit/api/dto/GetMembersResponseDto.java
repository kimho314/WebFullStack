package com.example.lunit.api.dto;

import lombok.Builder;

@Builder
public record GetMembersResponseDto(
        String userName,
        String email,
        Boolean isEnabled,
        Integer curAnalyzeCnt,
        Integer maxAnalyzeCnt
) {
}
