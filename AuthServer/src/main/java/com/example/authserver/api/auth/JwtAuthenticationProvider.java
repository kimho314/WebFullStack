package com.example.authserver.api.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authserver.api.base.exception.AccessJWTExpiredException;
import com.example.authserver.core.util.TokenProvider;
import com.example.authserver.domain.member.repository.BlackListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailService userDetailService;
    private final BlackListRepository blackListRepository;

    public JwtAuthenticationProvider(CustomUserDetailService userDetailService,
                                     BlackListRepository blackListRepository) {
        this.userDetailService = userDetailService;
        this.blackListRepository = blackListRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String jwtToken = jwtAuthenticationToken.getJwtToken();

        if (blackListRepository.existsByToken(jwtToken)) {
            throw new AccessJWTExpiredException("invalid access token");
        }

        DecodedJWT decodedJWT = TokenProvider.verify(jwtToken);
        String userId = decodedJWT.getSubject();

        UserDetails userDetails = userDetailService.loadUserByUsername(userId);
        List<String> roles = decodedJWT.getClaim(TokenProvider.JWT_ROLE).asList(String.class);
        log.info("userId : {}, role : {}", userId, roles);

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new JwtAuthenticationToken(userDetails, "", simpleGrantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
