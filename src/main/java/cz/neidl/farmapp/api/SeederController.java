package cz.neidl.farmapp.api;

import cz.neidl.farmapp.model.SeederRequestDto;
import cz.neidl.farmapp.model.SeederResponseDto;
import cz.neidl.farmapp.service.SeederService;
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
