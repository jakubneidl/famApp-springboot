package com.example.application.mapper;

import com.example.application.domain.PlantedCropPosition;
import com.example.application.model.PlantedCropPositionRequestDto;
import com.example.application.model.PlantedCropPositionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantedCropPositionMapper {
    PlantedCropPosition mapToDomain(PlantedCropPositionRequestDto source);
    PlantedCropPositionResponseDto mapToDto(PlantedCropPosition source);
}
