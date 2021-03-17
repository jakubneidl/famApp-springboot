package cz.neidl.farmapp.mapper;

import cz.neidl.farmapp.domain.Crop;
import cz.neidl.farmapp.model.CropRequestDto;
import cz.neidl.farmapp.model.CropResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropMapper {
    Crop mapToDomain(CropRequestDto source);
    CropResponseDto mapToDto(Crop source);
}
