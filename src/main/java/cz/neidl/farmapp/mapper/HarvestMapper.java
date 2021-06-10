package cz.neidl.farmapp.mapper;

import cz.neidl.farmapp.domain.Harvest;
import cz.neidl.farmapp.model.HarvestRequestDto;
import cz.neidl.farmapp.model.HarvestResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestResponseDto mapToDto(Harvest source);
    Harvest mapToDomain(HarvestRequestDto source);
}
