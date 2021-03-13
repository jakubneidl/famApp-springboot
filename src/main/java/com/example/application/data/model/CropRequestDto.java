package com.example.application.data.model;

import com.example.application.data.domain.Seeder;
import lombok.Data;

@Data
public class CropRequestDto {
    private String name;
    private String info;
    private String spacing;
    private String soilHumidity;
    private String idealSoilHumidity;
    private SeederRequestDto seeder;
}
