package com.example.lunit.common.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends ServiceException {
    public IncorrectPasswordException() {
        super(HttpStatus.BAD_REQUEST.value(), "password is incorrect");
    }
}
