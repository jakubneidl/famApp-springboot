package cz.neidl.farmapp.service;

import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.model.PlantedCropRequestDto;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import cz.neidl.farmapp.model.SensorReadingResponseDto;

import java.util.List;

public interface PlantedCropService {
    List<PlantedCropResponseDto> getAllCropsInHarvest(Long id);
    List<PlantedCropResponseDto> getAllPlantedCrops();
    PlantedCropResponseDto createPlantCrop(PlantedCropRequestDto cropToPlant);
    PlantedCropResponseDto plantCrop(PlantedCropRequestDto cropToPlant);
    List<SensorReadingResponseDto> getPlantedCropHumidity(Long id);
    void deletePlantedCrop(Long cropId);
}
