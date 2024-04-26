package com.example.lunit.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends ServiceException {
    public InvalidTokenException() {
        super(HttpStatus.BAD_REQUEST.value(), "invalid token type");
    }
}
