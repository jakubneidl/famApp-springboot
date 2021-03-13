package com.example.application.data.mapper;

import com.example.application.data.domain.PlantedCrop;
import com.example.application.data.model.PlantedCropRequestDto;
import com.example.application.data.model.PlantedCropResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantedCropMapper {
    PlantedCrop mapToDomain(PlantedCropRequestDto source);
    PlantedCropResponseDto mapToDto(PlantedCrop source);
}
