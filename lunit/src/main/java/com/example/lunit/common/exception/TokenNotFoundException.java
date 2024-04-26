package com.example.lunit.common.exception;

import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends ServiceException {
    public TokenNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "token not found");
    }
}
