package com.example.application.mapper;

import com.example.application.domain.Seeder;
import com.example.application.model.SeederRequestDto;
import com.example.application.model.SeederResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CropMapper.class})
public interface SeederMapper {
    SeederResponseDto mapToDto(Seeder source);
    Seeder mapToDomain(SeederRequestDto source);
}
