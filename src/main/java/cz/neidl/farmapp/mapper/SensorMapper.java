package cz.neidl.farmapp.mapper;


import cz.neidl.farmapp.domain.Sensor;
import cz.neidl.farmapp.model.SensorRequestDto;
import cz.neidl.farmapp.model.SensorResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SensorReadingMapper.class})
public interface SensorMapper {
    Sensor mapToDomain(SensorRequestDto source);
    SensorResponseDto mapToDto(Sensor source);
}
