package com.example.application.data.service;



import com.example.application.data.model.SensorReadingResponseDto;

import java.util.List;

public interface SensorReadingService {
    List<SensorReadingResponseDto> findAllReadings();
}
