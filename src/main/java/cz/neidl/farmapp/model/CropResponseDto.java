package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class CropResponseDto {
    private String name;
    private String info;
    private String spacing;
    private String soilHumidity;
    private String idealSoilHumidity;
    private SeederResponseDto seeder;
}
