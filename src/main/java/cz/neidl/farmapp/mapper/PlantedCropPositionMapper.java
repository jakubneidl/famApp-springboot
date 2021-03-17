package cz.neidl.farmapp.mapper;

import cz.neidl.farmapp.domain.PlantedCropPosition;
import cz.neidl.farmapp.model.PlantedCropPositionRequestDto;
import cz.neidl.farmapp.model.PlantedCropPositionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantedCropPositionMapper {
    PlantedCropPosition mapToDomain(PlantedCropPositionRequestDto source);
    PlantedCropPositionResponseDto mapToDto(PlantedCropPosition source);
}
