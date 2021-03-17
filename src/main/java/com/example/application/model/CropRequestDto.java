package com.example.application.model;

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
