package cz.neidl.farmapp.exception;

import org.springframework.http.HttpStatus;

public class SensorAlreadyExistsException extends FarmException{
    public SensorAlreadyExistsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
