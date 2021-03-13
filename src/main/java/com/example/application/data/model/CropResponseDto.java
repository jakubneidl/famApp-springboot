package com.example.application.data.model;

import com.example.application.data.domain.Seeder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CropResponseDto {
    private String name;
    private String info;
    private String spacing;
    private String soilHumidity;
    private String idealSoilHumidity;
    private SeederResponseDto seeder;
}
