package cz.neidl.farmapp.service;

import cz.neidl.farmapp.model.SeederRequestDto;
import cz.neidl.farmapp.model.SeederResponseDto;

import java.util.List;

public interface SeederService {
    List<SeederResponseDto> findAllSeeders();

//    SeederResponseDto saveSeeder(String name, String x, String y, String z);
    SeederResponseDto saveSeeder(SeederRequestDto seederRequestDto);

    SeederRequestDto findSeederByName(String seederName);
}
