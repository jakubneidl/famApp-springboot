package cz.neidl.farmapp.mapper;

import cz.neidl.farmapp.domain.SeederPosition;
import cz.neidl.farmapp.model.SeederPositionRequestDto;
import cz.neidl.farmapp.model.SeederPositionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeederPositionMapper {
    SeederPosition mapToDomain(SeederPositionRequestDto source);
    SeederPositionResponseDto mapToDto(SeederPosition source);
}
