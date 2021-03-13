package com.example.application.data.mapper;

import com.example.application.data.domain.Seeder;
import com.example.application.data.model.SeederRequestDto;
import com.example.application.data.model.SeederResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CropMapper.class})
public interface SeederMapper {
    SeederResponseDto mapToDto(Seeder source);
    Seeder mapToDomain(SeederRequestDto source);
}
