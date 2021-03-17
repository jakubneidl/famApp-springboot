package com.example.application.api;

import com.example.application.model.SeederRequestDto;
import com.example.application.model.SeederResponseDto;
import com.example.application.service.SeederService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeederController {

    private final SeederService seederService;
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/seeders")
    public List<SeederResponseDto> getAllSeeders(){
        return seederService.findAllSeeders();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/seeders")
    public SeederResponseDto saveSeeder(@RequestBody SeederRequestDto seederRequestDto){
        return seederService.saveSeeder(seederRequestDto);
    }
}
