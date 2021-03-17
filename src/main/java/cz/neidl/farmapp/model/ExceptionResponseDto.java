package cz.neidl.farmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponseDto {
    private final String message;
    private final int status;
    private final LocalDateTime timeStamp;
}
