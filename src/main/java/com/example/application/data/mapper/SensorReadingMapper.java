package com.example.application.data.mapper;



import com.example.application.data.domain.SensorReading;
import com.example.application.data.model.SensorReadingRequestDto;
import com.example.application.data.model.SensorReadingResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorReadingMapper {
    SensorReading mapToDomain(SensorReadingRequestDto source);
    SensorReadingResponseDto mapToDto(SensorReading source);
}
