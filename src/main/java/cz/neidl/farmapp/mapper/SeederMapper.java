package cz.neidl.farmapp.mapper;

import cz.neidl.farmapp.domain.Seeder;
import cz.neidl.farmapp.model.SeederRequestDto;
import cz.neidl.farmapp.model.SeederResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CropMapper.class})
public interface SeederMapper {
    SeederResponseDto mapToDto(Seeder source);
    Seeder mapToDomain(SeederRequestDto source);
}
