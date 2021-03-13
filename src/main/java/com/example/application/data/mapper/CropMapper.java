package com.example.application.data.mapper;

import com.example.application.data.domain.Crop;
import com.example.application.data.model.CropRequestDto;
import com.example.application.data.model.CropResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropMapper {
    Crop mapToDomain(CropRequestDto source);
    CropResponseDto mapToDto(Crop source);
}
