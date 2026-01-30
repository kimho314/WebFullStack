package com.luna.sessionusingdbredis.auth;

import com.luna.sessionusingdbredis.restapi.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestApiLoginAuthFilter  extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RestApiLoginAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);
    }


    @Override
    public @Nullable Authentication attemptAuthentication(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(!request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)){
            throw new AuthenticationServiceException("Unsupported Content-Type: " + request.getContentType());
        }

        LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain, @NonNull Authentication authResult) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authResult);
        HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();
        contextRepository.saveContext(securityContext, request, response);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(request.getSession().getId());
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.getWriter().write("Not authecticated");
        response.getWriter().flush();
    }
}
