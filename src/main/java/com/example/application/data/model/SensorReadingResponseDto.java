package com.example.application.data.model;

import com.example.application.data.domain.Sensor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SensorReadingResponseDto {
    private String reading;
    private Sensor sensor;
}
