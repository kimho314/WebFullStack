package com.example.lunit.domain.repository;

import com.example.lunit.common.enums.TokenType;
import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByJwtToken(String token);

    Optional<Token> findByMemberAndTokenType(Member member, TokenType tokenType);

    void deleteAllByMember(Member member);

    void deleteByMemberAndTokenType(Member member, TokenType tokenType);
}
