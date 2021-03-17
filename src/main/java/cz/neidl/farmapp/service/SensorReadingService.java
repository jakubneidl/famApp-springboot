package cz.neidl.farmapp.service;



import cz.neidl.farmapp.model.SensorReadingResponseDto;

import java.util.List;

public interface SensorReadingService {
    List<SensorReadingResponseDto> findAllReadings();
}
