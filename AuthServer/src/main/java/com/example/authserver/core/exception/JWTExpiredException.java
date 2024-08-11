package com.example.authserver.core.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTExpiredException extends JWTVerificationException {
    public JWTExpiredException(String message) {
        super(message);
    }
}
