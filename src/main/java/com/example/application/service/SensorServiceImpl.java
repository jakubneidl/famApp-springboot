package com.example.application.service;

import com.example.application.domain.Sensor;
import com.example.application.domain.SensorReading;
import com.example.application.exception.SensorAlreadyExistsException;
import com.example.application.exception.SensorNotFoundException;
import com.example.application.mapper.SensorMapper;
import com.example.application.model.SensorRequestDto;
import com.example.application.model.SensorResponseDto;
import com.example.application.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    @Override
    public SensorResponseDto findByName(String sensorName) {
        Sensor sensor = sensorRepository.findBySensorName(sensorName)
                .orElseThrow(() -> new SensorNotFoundException("Sensor couldn't be found"));
        return sensorMapper.mapToDto(sensor);
    }

    @Override
    public List<SensorResponseDto> findAllSensors() {
        return sensorRepository.findAll().stream()
                .map(sensorMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorResponseDto> findAllSensors(String filterText) {
        if(filterText == null  || filterText.isEmpty()) {
            return sensorRepository.findAll().stream()
                    .map(sensorMapper::mapToDto)
                    .collect(Collectors.toList());
        }else {
            return sensorRepository.findBySensorName(filterText).stream()
                    .map(sensorMapper::mapToDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    @Transactional
    public SensorResponseDto saveSensor(SensorRequestDto sensorRequestDto) {
        if(sensorRepository.findBySensorName(sensorRequestDto.getSensorName()).isEmpty()) {
            Sensor sensor = sensorMapper.mapToDomain(sensorRequestDto);
            SensorResponseDto sensorResponseDto = sensorMapper.mapToDto(sensorRepository.save(sensor));
            return sensorResponseDto;
        }else throw new SensorAlreadyExistsException("Sensor with this name already exists");
    }

    @Override
    @Transactional
    public SensorResponseDto updateData(SensorRequestDto sensorRequestDto) {
        Sensor sensorToUpdate = sensorRepository.findBySensorName(sensorRequestDto.getSensorName())
                .orElseThrow(() -> new SensorNotFoundException("Sensor with this name couldn't be found, can't update data"));

        Set<SensorReading> sensorReadingSetToUpdate = sensorToUpdate.getSensorReadingSet();

        Sensor sensorDomain = sensorMapper.mapToDomain(sensorRequestDto);
        sensorReadingSetToUpdate.add(sensorDomain.getSensorReadingSet().stream().iterator().next());

        return sensorMapper.mapToDto(sensorToUpdate);
    }

    @Override
    public List<Sensor> findAllDomainSensors() {
        return sensorRepository.findAll();
    }
}
