package com.example.application.service;

import com.example.application.domain.Sensor;
import com.example.application.model.SensorRequestDto;
import com.example.application.model.SensorResponseDto;

import java.util.List;

public interface SensorService {
    SensorResponseDto findByName(String sensorName);
    List<SensorResponseDto> findAllSensors();
    List<SensorResponseDto> findAllSensors(String filterText);
    SensorResponseDto saveSensor(SensorRequestDto sensorRequestDto);
    SensorResponseDto updateData(SensorRequestDto sensorRequestDto);
    List<Sensor> findAllDomainSensors();
}
