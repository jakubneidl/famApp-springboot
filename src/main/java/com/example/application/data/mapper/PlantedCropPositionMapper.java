package com.example.application.data.mapper;

import com.example.application.data.domain.PlantedCropPosition;
import com.example.application.data.model.PlantedCropPositionRequestDto;
import com.example.application.data.model.PlantedCropPositionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantedCropPositionMapper {
    PlantedCropPosition mapToDomain(PlantedCropPositionRequestDto source);
    PlantedCropPositionResponseDto mapToDto(PlantedCropPosition source);
}
