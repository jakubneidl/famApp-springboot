package cz.neidl.farmapp.exception;

import org.springframework.http.HttpStatus;

public class PositionNotFoundException extends FarmException {
    public PositionNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
