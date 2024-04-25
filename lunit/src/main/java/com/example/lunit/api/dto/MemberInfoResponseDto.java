package com.example.lunit.api.dto;

import com.example.lunit.common.enums.ResultStatus;
import com.example.lunit.common.enums.Role;

public record MemberInfoResponseDto(
        ResultStatus status,
        Integer statusCode,
        String userName,
        Boolean isEnabled,
        String email,
        Role role,
        Integer maxAnalyzeCnt,
        Integer curAnalyzeCnt
) {
}
