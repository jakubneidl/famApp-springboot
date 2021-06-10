package cz.neidl.farmapp.model;

import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.domain.Sensor;
import lombok.Data;

import java.util.List;

@Data
public class HarvestResponseDto {
    private Long id;
    private String name;
}
