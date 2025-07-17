package com.example.lpost.common.exception;

import java.util.NoSuchElementException;

public class NoPostException extends NoSuchElementException {
    public NoPostException(String message) {
        super(message);
    }
}
