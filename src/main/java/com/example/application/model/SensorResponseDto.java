package com.example.application.model;

import lombok.Data;

import java.util.Set;

@Data
public class SensorResponseDto {
    private String sensorName;
    private Set<SensorReadingResponseDto> sensorReadingSet;
}
