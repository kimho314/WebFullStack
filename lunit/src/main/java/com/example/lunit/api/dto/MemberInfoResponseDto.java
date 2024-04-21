package com.example.lunit.api.dto;

import com.example.lunit.common.enums.Role;

public record MemberInfoResponseDto(
        String userName,
        Boolean isEnabled,
        String email,
        Role role,
        Integer max_analyze_cnt,
        Integer cur_analyze_cnt
) {
}
