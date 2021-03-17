package com.example.application.mapper;

import com.example.application.domain.Crop;
import com.example.application.model.CropRequestDto;
import com.example.application.model.CropResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropMapper {
    Crop mapToDomain(CropRequestDto source);
    CropResponseDto mapToDto(Crop source);
}
