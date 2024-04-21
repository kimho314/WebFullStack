package com.example.lunit.common.component;

import com.example.lunit.common.dto.CommonResponseDto;
import com.example.lunit.common.dto.ErrorResult;
import com.example.lunit.common.util.ObjectMapperFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResult errorResult = new ErrorResult(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        String result = ObjectMapperFactory.getInstance().writeValueAsString(new CommonResponseDto<>(errorResult));
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().println(result);
    }
}
