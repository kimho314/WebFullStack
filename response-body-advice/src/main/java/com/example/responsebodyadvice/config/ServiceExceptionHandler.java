package com.example.responsebodyadvice.config;

import com.example.responsebodyadvice.constants.CommonResponse;
import com.example.responsebodyadvice.constants.ResponseCode;
import com.example.responsebodyadvice.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException exception) {
        var body = CommonResponse.builder()
                .responseCode(exception.getResponseCode().getValue())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(body, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error("handleException : {}", exception.getLocalizedMessage());
        var body = CommonResponse.builder()
                .responseCode(ResponseCode.COMMON_INTERNAL_SERVER_ERROR.getValue())
                .message(ResponseCode.COMMON_INTERNAL_SERVER_ERROR.name())
                .build();
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
