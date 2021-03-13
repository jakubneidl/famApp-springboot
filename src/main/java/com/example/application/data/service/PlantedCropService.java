package com.example.application.data.service;

import com.example.application.data.domain.Crop;
import com.example.application.data.model.*;

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
