package com.example.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantedCropRequestDto {
    private CropRequestDto crop;
    private PlantedCropPositionRequestDto plantedCropPosition;
    private boolean  planted;
}
