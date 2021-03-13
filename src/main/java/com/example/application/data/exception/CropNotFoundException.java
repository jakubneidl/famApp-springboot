package com.example.application.data.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

public class CropNotFoundException extends FarmException {
    public CropNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
