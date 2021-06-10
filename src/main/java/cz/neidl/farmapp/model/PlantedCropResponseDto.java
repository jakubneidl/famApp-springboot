package cz.neidl.farmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlantedCropResponseDto {
    private Long id;
    private CropResponseDto crop;
    private PlantedCropPositionResponseDto plantedCropPosition;
    private boolean planted;
    private List<SensorReadingResponseDto> soilHumidityReadings;
    HarvestResponseDto harvestResponseDto;
}
