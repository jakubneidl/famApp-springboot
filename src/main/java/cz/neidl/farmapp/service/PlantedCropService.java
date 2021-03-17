package cz.neidl.farmapp.service;

import cz.neidl.farmapp.model.PlantedCropPositionRequestDto;
import cz.neidl.farmapp.model.PlantedCropRequestDto;
import cz.neidl.farmapp.model.PlantedCropResponseDto;

import java.util.List;

public interface PlantedCropService {
    List<PlantedCropResponseDto> getAllPlantedCrops();

    PlantedCropResponseDto createPlantCrop(PlantedCropRequestDto cropToPlant);
    PlantedCropResponseDto plantCrop(PlantedCropRequestDto cropToPlant);

    void plantCropVaaddin(String value, String x, String y, String z);

    void plantCropFromVaaddin(String cropName, String positionX, String positionY, String positionZ);

    void deleteFromVaadin(PlantedCropPositionRequestDto plantedCropPositionRequestDto);

//    PlantedCropResponseDto plantCrop(CropRequestDto cropToPlant);
}
