package cz.neidl.farmapp.exception;

import org.springframework.http.HttpStatus;

public class SensorNotFoundException extends FarmException{
    public SensorNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
