package com.example.lunit.common.exception;

import org.springframework.http.HttpStatus;

public class DisabledMemberException extends ServiceException {
    public DisabledMemberException() {
        super(HttpStatus.BAD_REQUEST.value(), "member is disabled");
    }
}
