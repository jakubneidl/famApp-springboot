package cz.neidl.farmapp.service;

import cz.neidl.farmapp.domain.Sensor;
import cz.neidl.farmapp.model.SensorRequestDto;
import cz.neidl.farmapp.model.SensorResponseDto;

import java.util.List;

public interface SensorService {
    SensorResponseDto findByName(String sensorName);
    List<SensorResponseDto> findAllSensors();
    List<SensorResponseDto> findAllSensors(String filterText);
    SensorResponseDto saveSensor(SensorRequestDto sensorRequestDto);
    SensorResponseDto updateData(SensorRequestDto sensorRequestDto);
    List<Sensor> findAllDomainSensors();
}
