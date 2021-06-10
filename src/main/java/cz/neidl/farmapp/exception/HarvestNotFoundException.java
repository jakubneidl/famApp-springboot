package cz.neidl.farmapp.exception;

import org.springframework.http.HttpStatus;

public class HarvestNotFoundException extends FarmException{
    public HarvestNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
