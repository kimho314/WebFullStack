package com.example.responsebodyadvice.config;

import com.example.responsebodyadvice.constants.CommonResponse;
import com.example.responsebodyadvice.constants.ResponseCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ServiceResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final String RESPONSE_OK_MESSAGE = "ok";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(CommonResponse.class) &&
                !returnType.getParameterType().equals(ResponseEntity.class);
    }

    /**
     * optional type은 자동 converting 가능 -> 핸들링 필요 없음
     * String 타입은 StringHttpMessageConvert 때문에 String을 바로 return 해야함
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        var commonResponseBuilder = CommonResponse.builder()
                .responseCode(ResponseCode.COMMON_OK.getValue())
                .message(RESPONSE_OK_MESSAGE);
        if (returnType.isOptional()) {
            log.info("optional body : {}", body);
        }

        if (returnType.getParameterType().equals(String.class)) {
            try {
                return OBJECT_MAPPER.writeValueAsString(body);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        if (Objects.isNull(body)) {
            return commonResponseBuilder.build();
        }

        return commonResponseBuilder
                .result(body)
                .build();
    }
}
