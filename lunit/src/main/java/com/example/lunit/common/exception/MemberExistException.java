package com.example.lunit.common.exception;

import org.springframework.http.HttpStatus;

public class MemberExistException extends ServiceException {
    public MemberExistException() {
        super(HttpStatus.BAD_REQUEST.value(), "member exists");
    }
}
