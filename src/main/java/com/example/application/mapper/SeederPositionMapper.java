package com.example.application.mapper;

import com.example.application.domain.SeederPosition;
import com.example.application.model.SeederPositionRequestDto;
import com.example.application.model.SeederPositionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeederPositionMapper {
    SeederPosition mapToDomain(SeederPositionRequestDto source);
    SeederPositionResponseDto mapToDto(SeederPosition source);
}
