package com.example.application.model;

import lombok.Data;

import java.util.Set;

@Data
public class SensorRequestDto {
    private String sensorName;
    private Set<SensorReadingRequestDto> sensorReadingSet;
}
