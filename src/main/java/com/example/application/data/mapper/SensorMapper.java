package com.example.application.data.mapper;


import com.example.application.data.domain.Sensor;
import com.example.application.data.model.SensorRequestDto;
import com.example.application.data.model.SensorResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SensorReadingMapper.class})
public interface SensorMapper {
    Sensor mapToDomain(SensorRequestDto source);
    SensorResponseDto mapToDto(Sensor source);
}
