package com.example.lunit.api.mapper;

import com.example.lunit.common.enums.Role;
import com.example.lunit.domain.entity.Member;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberMapper {
    public final Integer DEFAULT_MAX_ANALYZE_CNT = 10;
    public final Integer DEFAULT_CUR_ANALYZE_CNT = 0;
    public final Long DEFAULT_EXPIRE_DURATION = 3L * 60L * 60L;

    public Member signupMapper(String username, String password, Role role, String email) {
        return Member.builder()
                .userName(username)
                .password(password)
                .role(role)
                .email(email)
                .expireDuration(DEFAULT_EXPIRE_DURATION)
                .curAnalyzeCnt(DEFAULT_CUR_ANALYZE_CNT)
                .maxAnalyzeCnt(DEFAULT_MAX_ANALYZE_CNT)
                .isEnabled(Boolean.TRUE)
                .build();
    }
}
