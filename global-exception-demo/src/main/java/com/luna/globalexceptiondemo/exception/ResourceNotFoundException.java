package com.luna.globalexceptiondemo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        String msg = "Product with ID " + id + " not found.";
        super(msg);
    }
}
