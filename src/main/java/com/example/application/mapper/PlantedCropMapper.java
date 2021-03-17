package com.example.application.mapper;

import com.example.application.domain.PlantedCrop;
import com.example.application.model.PlantedCropRequestDto;
import com.example.application.model.PlantedCropResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantedCropMapper {
    PlantedCrop mapToDomain(PlantedCropRequestDto source);
    PlantedCropResponseDto mapToDto(PlantedCrop source);
}
