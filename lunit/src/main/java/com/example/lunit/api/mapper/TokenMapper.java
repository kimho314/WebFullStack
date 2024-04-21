package com.example.lunit.api.mapper;

import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.entity.Token;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.util.Date;


@UtilityClass
public class TokenMapper {

    public Token tokenMapper(String accessToken, Member member, Date date) {
        return Token.builder()
                .member(member)
                .accessToken(accessToken)
                .expiresAt(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .build();
    }
}
