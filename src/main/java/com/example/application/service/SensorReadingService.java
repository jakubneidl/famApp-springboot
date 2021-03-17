package com.example.application.service;



import com.example.application.model.SensorReadingResponseDto;

import java.util.List;

public interface SensorReadingService {
    List<SensorReadingResponseDto> findAllReadings();
}
