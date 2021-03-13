package com.example.application.data.exception;

import org.springframework.http.HttpStatus;

public class CropAlreadyExistsException extends FarmException{
    public CropAlreadyExistsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
