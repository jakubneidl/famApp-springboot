package com.example.application.mapper;


import com.example.application.domain.Sensor;
import com.example.application.model.SensorRequestDto;
import com.example.application.model.SensorResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SensorReadingMapper.class})
public interface SensorMapper {
    Sensor mapToDomain(SensorRequestDto source);
    SensorResponseDto mapToDto(Sensor source);
}
