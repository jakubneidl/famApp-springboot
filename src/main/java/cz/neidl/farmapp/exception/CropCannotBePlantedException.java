package cz.neidl.farmapp.exception;

import org.springframework.http.HttpStatus;

public class CropCannotBePlantedException extends FarmException{
    public CropCannotBePlantedException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
