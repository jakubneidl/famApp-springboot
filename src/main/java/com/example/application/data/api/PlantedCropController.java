package com.example.application.data.api;

import com.example.application.data.model.PlantedCropPositionRequestDto;
import com.example.application.data.model.PlantedCropRequestDto;
import com.example.application.data.model.PlantedCropResponseDto;
import com.example.application.data.service.PlantedCropService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlantedCropController {
    private final PlantedCropService plantedCropService;
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/plantedCrops")
    public List<PlantedCropResponseDto> getAllPlantedCrops(){
       return plantedCropService.getAllPlantedCrops();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/createPlantedCrops")
    public PlantedCropResponseDto createPlantCrop(@RequestBody PlantedCropRequestDto cropToPlant){
        return plantedCropService.createPlantCrop(cropToPlant);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/plantedCrops")
    public PlantedCropResponseDto plantCrop(@RequestBody PlantedCropRequestDto cropToPlant){
        return plantedCropService.plantCrop(cropToPlant);
    }


}
