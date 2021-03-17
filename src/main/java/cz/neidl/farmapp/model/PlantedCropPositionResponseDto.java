package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class PlantedCropPositionResponseDto {
    private String positionX;
    private String positionY;
    private String positionZ;
//    private PlantedCropResponseDto plantedCrop;
}
