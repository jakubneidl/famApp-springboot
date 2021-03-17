package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class PlantedCropPositionRequestDto {
    private String positionX;
    private String positionY;
    private String positionZ;
//    private PlantedCropRequestDto plantedCrop;
}
