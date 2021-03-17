package com.example.application.model;

import com.example.application.domain.Sensor;
import lombok.Data;

@Data
public class SensorReadingResponseDto {
    private String reading;
    private Sensor sensor;
}
