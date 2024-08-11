package com.example.authserver.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.authserver.api.base.exception.AccessJWTExpiredException;
import com.example.authserver.domain.member.enums.Role;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@UtilityClass
public class TokenProvider {
    public static final String JWT_SECRET_KEY = "secret_key";
    public static final String JWT_ROLE = "roles";
    public static final int ACCESS_TOKEN_EXPIRATION_IN_SECONDS = 2 * 60 * 60;
//    public static final int ACCESS_TOKEN_EXPIRATION_IN_SECONDS = 10;

    public String create(
            String userId,
            List<Role> roles,
            LocalDateTime issuedAt,
            LocalDateTime expireAt
    ) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            return JWT.create()
                    .withSubject(userId)
                    .withIssuedAt(issuedAt.toInstant(ZoneOffset.ofHours(+9)))
                    .withExpiresAt(expireAt.toInstant(ZoneOffset.ofHours(+9)))
                    .withClaim("roles", roles.stream().map(Enum::name).toList())
                    .sign(algorithm);
        }
        catch (JWTCreationException ex) {
            throw new JWTCreationException(userId + " token creation error", ex);
        }
    }

    public DecodedJWT verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            return verifier.verify(token);
        }
        catch (JWTVerificationException exception) {
            if (exception.getMessage().contains("expired")) {
                throw new AccessJWTExpiredException(exception.getMessage());
            }
            else {
                throw new JWTVerificationException(exception.getMessage());
            }
        }
    }
}
