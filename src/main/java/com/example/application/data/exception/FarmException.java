package com.example.application.data.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class FarmException extends RuntimeException{
    private HttpStatus status;
    private String message;

}
