package com.example.lunit.common.advice;

import com.example.lunit.common.dto.CommonResponseDto;
import com.example.lunit.common.dto.ErrorResult;
import com.example.lunit.common.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<CommonResponseDto<ErrorResult>> ServiceException(ServiceException e) {
        ErrorResult errorResult = new ErrorResult(e.getStatusCode(), e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(new CommonResponseDto<>(errorResult));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDto<ErrorResult>> exception(Exception e) {
        ErrorResult errorResult = new ErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return ResponseEntity.internalServerError().body(new CommonResponseDto<>(errorResult));
    }
}
