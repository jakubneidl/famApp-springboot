package cz.neidl.farmapp.api;


import cz.neidl.farmapp.exception.FarmException;
import cz.neidl.farmapp.model.ExceptionResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {FarmException.class})
    protected ResponseEntity<Object> handleFarmException(FarmException farmEx, WebRequest request){
        return handleExceptionInternal(farmEx, buildResponse(farmEx), new HttpHeaders(), farmEx.getStatus(), request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleExceptions(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ExceptionResponseDto buildResponse(FarmException exception){
        return new ExceptionResponseDto(exception.getMessage(),exception.getStatus().value(), LocalDateTime.now());
    }
}
