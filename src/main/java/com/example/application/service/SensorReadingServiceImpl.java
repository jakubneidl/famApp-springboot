package com.example.application.service;


import com.example.application.mapper.SensorReadingMapper;
import com.example.application.model.SensorReadingResponseDto;
import com.example.application.repository.SensorReadingRepository;
import com.example.application.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorReadingServiceImpl implements SensorReadingService {
    private final SensorReadingRepository sensorReadingRepository;
    private final SensorReadingMapper sensorReadingMapper;
    private final SensorRepository sensorRepository;

    @Override
    public List<SensorReadingResponseDto> findAllReadings() {
        List<SensorReadingResponseDto> collect = sensorReadingRepository.findAll().stream()
                .map(sensorReadingMapper::mapToDto)
                .collect(Collectors.toList());
        return collect;
    }
}
