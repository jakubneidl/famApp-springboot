package com.example.application.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlantedCropResponseDto {
    private CropResponseDto crop;
    private PlantedCropPositionResponseDto plantedCropPosition;
    private boolean planted;
}
