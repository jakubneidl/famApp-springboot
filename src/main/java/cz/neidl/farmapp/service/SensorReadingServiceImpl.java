package cz.neidl.farmapp.service;


import cz.neidl.farmapp.mapper.SensorReadingMapper;
import cz.neidl.farmapp.model.SensorReadingResponseDto;
import cz.neidl.farmapp.repository.SensorReadingRepository;
import cz.neidl.farmapp.repository.SensorRepository;
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
