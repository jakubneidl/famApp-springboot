package cz.neidl.farmapp.mapper;

import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.model.PlantedCropRequestDto;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantedCropMapper {
    PlantedCrop mapToDomain(PlantedCropRequestDto source);
    PlantedCropResponseDto mapToDto(PlantedCrop source);
}
