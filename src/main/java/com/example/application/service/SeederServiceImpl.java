package com.example.application.service;

import com.example.application.domain.Seeder;
import com.example.application.mapper.SeederMapper;
import com.example.application.model.SeederRequestDto;
import com.example.application.model.SeederResponseDto;
import com.example.application.repository.SeederRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeederServiceImpl implements SeederService {

    private final SeederRepository seederRepository;
    private final SeederMapper seederMapper;


    @Override
    public List<SeederResponseDto> findAllSeeders(){
        return seederRepository.findAll()
                .stream()
                .map(seederMapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public SeederResponseDto saveSeeder(SeederRequestDto seederRequestDto) {
        return seederMapper.mapToDto(seederRepository.save(seederMapper.mapToDomain(seederRequestDto)));
    }

    @Override
    public SeederRequestDto findSeederByName(String seederName) {
        Seeder seeder = seederRepository.findByName(seederName).get();
        SeederRequestDto seederRequestDto = new SeederRequestDto();
        seederRequestDto.setName(seeder.getName());
        return seederRequestDto;
    }

//    @Transactional
//    @Override
//    public SeederResponseDto saveSeeder(String name, String x, String y, String z){
//
//        if(seederRepository.findByName(name).isEmpty()){
//            return  seederMapper.mapToDto(seederRepository.save(new Seeder(name, x, y, z,)));
//        }
//        return null;
//    }

}
