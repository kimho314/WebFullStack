package com.example.authserver.api.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authserver.core.util.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        DecodedJWT decodedJWT = TokenProvider.verify(jwtAuthenticationToken.getJwtToken());
        String userId = decodedJWT.getSubject();
        String role = decodedJWT.getClaim(TokenProvider.JWT_ROLE).asString();
        log.info("userId : {}, role : {}", userId, role);
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);

        return new JwtAuthenticationToken(userId, "", List.of(simpleGrantedAuthority));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
