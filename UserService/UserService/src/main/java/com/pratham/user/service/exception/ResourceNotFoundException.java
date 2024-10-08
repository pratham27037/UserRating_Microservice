package com.pratham.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Resource not found in the server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
