package com.example.authserver.api.auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.authserver.api.base.dto.ErrorDto;
import com.example.authserver.api.base.exception.AccessJWTExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwt = null;
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            jwt = bearerToken.substring(7);
        }
        log.info("jwt : {}", jwt);
        if (StringUtils.hasText(jwt)) {
            try {
                JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt);
                Authentication authenticate = authenticationManager.authenticate(jwtAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
            catch (AccessJWTExpiredException e) {
                makeJWTVerifyExceptionResponse(response, 401, e);
            }
            catch (JWTVerificationException e) {
                makeJWTVerifyExceptionResponse(response, 402, e);
            }

        }

        filterChain.doFilter(request, response);
    }

    private void makeJWTVerifyExceptionResponse(
            HttpServletResponse response,
            int code,
            Exception e
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorDto errorDto = ErrorDto.builder()
                .code(code)
                .message(e.getMessage())
                .build();
        response.getWriter().write(objectMapper.writeValueAsString(errorDto));
        response.flushBuffer();
    }
}
