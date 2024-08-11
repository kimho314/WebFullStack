package com.example.authserver;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authserver.core.util.TokenProvider;
import com.example.authserver.domain.member.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class JwtTokenTest {
    @Test
    void createTokenTest() {
        final String testUserId = "hoseop";
        String token = TokenProvider.create(
                testUserId,
                List.of(Role.ROLE_USER),
                LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(TokenProvider.ACCESS_TOKEN_EXPIRATION_IN_SECONDS));
        System.out.println(token);
    }

    @Test
    void verifyTokenTest() {
        final String testUserId = "hoseop";
        String token = TokenProvider.create(testUserId,
                List.of(Role.ROLE_USER),
                LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(TokenProvider.ACCESS_TOKEN_EXPIRATION_IN_SECONDS));
        System.out.println(token);
        DecodedJWT decodedJWT = TokenProvider.verify(token);
        Assertions.assertEquals(testUserId, decodedJWT.getSubject());
    }
}
