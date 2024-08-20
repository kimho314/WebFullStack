package com.example.authserver.api.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class AccessJWTExpiredException extends JWTVerificationException {
    public AccessJWTExpiredException(String message) {
        super(message);
    }
}
