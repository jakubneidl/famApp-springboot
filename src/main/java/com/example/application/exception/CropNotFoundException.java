package com.example.application.exception;

import org.springframework.http.HttpStatus;

public class CropNotFoundException extends FarmException {
    public CropNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
