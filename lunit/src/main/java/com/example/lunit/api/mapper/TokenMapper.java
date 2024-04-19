package com.example.lunit.api.mapper;

import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.entity.Token;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenMapper {

    public Token tokenMapper(String accessToken, Member member) {
        return Token.builder()
                .member(member)
                .accessToken(accessToken)
                .build();
    }
}
