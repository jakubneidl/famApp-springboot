package com.example.application.data.exception;

import org.springframework.http.HttpStatus;

public class SeederNotFoundException extends FarmException{
    public SeederNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
