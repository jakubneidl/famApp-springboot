package com.example.application.data.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class SensorRequestDto {
    private String sensorName;
    private Set<SensorReadingRequestDto> sensorReadingSet;
}
