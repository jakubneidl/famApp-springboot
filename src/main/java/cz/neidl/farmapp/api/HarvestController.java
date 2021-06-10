package cz.neidl.farmapp.api;

import cz.neidl.farmapp.model.HarvestRequestDto;
import cz.neidl.farmapp.model.HarvestResponseDto;
import cz.neidl.farmapp.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HarvestController {
    private final HarvestService harvestService;
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/harvests")
    public List<HarvestResponseDto> getAllHarvests(){
        return harvestService.getAllHarvests();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/harvests/{id}")
    public HarvestResponseDto getHarvest(@PathVariable Long id){
        return harvestService.getHarvest(id);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/harvests")
    public HarvestResponseDto createNewHarvest(@RequestBody HarvestRequestDto harvestRequestDto){
        return harvestService.createNewHarvest(harvestRequestDto);
    }
}
