package cz.neidl.farmapp.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SensorReadingResponseDto {
    private String reading;
    private String value;
    private LocalDateTime createdAt;
}
