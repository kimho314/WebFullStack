package com.example.responsebodyadvice.exception;

import com.example.responsebodyadvice.constants.ResponseCode;
import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ResponseCode responseCode;
    private final String message;

    public ServiceException(HttpStatus httpStatus, ResponseCode responseCode, String message) {
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
        this.message = message;
    }

    public ServiceException(ResponseCode responseCode, String message) {
        this.httpStatus = HttpStatus.OK;
        this.responseCode = responseCode;
        this.message = message;
    }

    public ServiceException(ResponseCode responseCode, Throwable throwable) {
        this.httpStatus = HttpStatus.OK;
        this.responseCode = responseCode;
        this.message = throwable.getMessage();
    }
}
