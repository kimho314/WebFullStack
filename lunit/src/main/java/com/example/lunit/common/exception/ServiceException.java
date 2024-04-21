package com.example.lunit.common.exception;

import com.example.lunit.common.enums.ResultStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException {
    private String status;
    private int statusCode;
    private String message;

    public ServiceException(int statusCode, String message) {
        super(message);
        this.status = ResultStatus.FAIL.name();
        this.statusCode = statusCode;
        this.message = message;
    }
}
