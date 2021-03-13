package com.example.application.data.service;

import com.example.application.data.model.SeederRequestDto;
import com.example.application.data.model.SeederResponseDto;

import java.util.List;

public interface SeederService {
    List<SeederResponseDto> findAllSeeders();

//    SeederResponseDto saveSeeder(String name, String x, String y, String z);
    SeederResponseDto saveSeeder(SeederRequestDto seederRequestDto);

    SeederRequestDto findSeederByName(String seederName);
}
