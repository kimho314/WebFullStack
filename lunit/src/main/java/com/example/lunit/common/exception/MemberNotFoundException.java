package com.example.lunit.common.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends ServiceException {
    public MemberNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "member not found");
    }
}
