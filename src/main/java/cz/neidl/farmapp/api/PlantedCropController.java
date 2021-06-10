package cz.neidl.farmapp.api;

import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.model.PlantedCropRequestDto;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import cz.neidl.farmapp.model.SensorReadingResponseDto;
import cz.neidl.farmapp.service.PlantedCropService;
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
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "plantedCrops/humidity/{id}")
    public List<SensorReadingResponseDto> getPlantedCropHumidity(@PathVariable Long id){
       return plantedCropService.getPlantedCropHumidity(id);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(path = "/plantedCrops/{cropId}")
    public void deletePlantedCrop(@PathVariable Long cropId){
        plantedCropService.deletePlantedCrop(cropId);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/plantedCrops/harvest/{id}")
    public List<PlantedCropResponseDto> getAllCropsInHarvest(@PathVariable Long id){
        return plantedCropService.getAllCropsInHarvest(id);
    }


}
