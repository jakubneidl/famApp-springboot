package com.example.application.data.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class SensorResponseDto {
    private String sensorName;
    private Set<SensorReadingResponseDto> sensorReadingSet;
}
