package cz.neidl.farmapp.service;

import cz.neidl.farmapp.domain.Sensor;
import cz.neidl.farmapp.domain.SensorReading;
import cz.neidl.farmapp.exception.SensorAlreadyExistsException;
import cz.neidl.farmapp.exception.SensorNotFoundException;
import cz.neidl.farmapp.mapper.SensorMapper;
import cz.neidl.farmapp.model.SensorRequestDto;
import cz.neidl.farmapp.model.SensorResponseDto;
import cz.neidl.farmapp.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

        List<SensorReading> sensorReadingSetToUpdate = sensorToUpdate.getSensorReadings();

        Sensor sensorDomain = sensorMapper.mapToDomain(sensorRequestDto);
        sensorReadingSetToUpdate.add(sensorDomain.getSensorReadings().stream().iterator().next());

        return sensorMapper.mapToDto(sensorToUpdate);
    }

    @Override
    public List<Sensor> findAllDomainSensors() {
        return sensorRepository.findAll();
    }
}
