package com.example.lunit.common.component;

import com.example.lunit.common.dto.CommonResponseDto;
import com.example.lunit.common.dto.ErrorResult;
import com.example.lunit.common.util.ObjectMapperFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResult errorResult = new ErrorResult(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        String result = ObjectMapperFactory.getInstance().writeValueAsString(new CommonResponseDto<>(errorResult));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().println(result);
    }
}
