package cz.neidl.farmapp.mapper;



import cz.neidl.farmapp.domain.SensorReading;
import cz.neidl.farmapp.model.SensorReadingRequestDto;
import cz.neidl.farmapp.model.SensorReadingResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorReadingMapper {
    SensorReading mapToDomain(SensorReadingRequestDto source);
    SensorReadingResponseDto mapToDto(SensorReading source);
}
