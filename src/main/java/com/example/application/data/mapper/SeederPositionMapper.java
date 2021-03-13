package com.example.application.data.mapper;

import com.example.application.data.domain.SeederPosition;
import com.example.application.data.model.SeederPositionRequestDto;
import com.example.application.data.model.SeederPositionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeederPositionMapper {
    SeederPosition mapToDomain(SeederPositionRequestDto source);
    SeederPositionResponseDto mapToDto(SeederPosition source);
}
