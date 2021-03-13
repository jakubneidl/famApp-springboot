package com.example.application.data.exception;

import org.springframework.http.HttpStatus;

public class CropAlreadyPlantedInThisPosition extends FarmException {
    public CropAlreadyPlantedInThisPosition(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
