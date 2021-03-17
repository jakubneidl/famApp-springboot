package com.example.application.mapper;



import com.example.application.domain.SensorReading;
import com.example.application.model.SensorReadingRequestDto;
import com.example.application.model.SensorReadingResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorReadingMapper {
    SensorReading mapToDomain(SensorReadingRequestDto source);
    SensorReadingResponseDto mapToDto(SensorReading source);
}
