package com.example.application.data.service;

import com.example.application.data.domain.Sensor;
import com.example.application.data.model.SensorRequestDto;
import com.example.application.data.model.SensorResponseDto;

import java.util.List;

public interface SensorService {
    SensorResponseDto findByName(String sensorName);
    List<SensorResponseDto> findAllSensors();
    List<SensorResponseDto> findAllSensors(String filterText);
    SensorResponseDto saveSensor(SensorRequestDto sensorRequestDto);
    SensorResponseDto updateData(SensorRequestDto sensorRequestDto);
    List<Sensor> findAllDomainSensors();
}
