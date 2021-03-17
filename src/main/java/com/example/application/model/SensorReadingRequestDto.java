package com.example.application.model;

import com.example.application.domain.Sensor;
import lombok.Data;

@Data
public class SensorReadingRequestDto {
    private String reading;
    private Sensor sensor;
}
