package com.example.application.data.model;

import com.example.application.data.domain.Sensor;
import lombok.Data;

@Data
public class SensorReadingRequestDto {
    private String reading;
    private Sensor sensor;
}
