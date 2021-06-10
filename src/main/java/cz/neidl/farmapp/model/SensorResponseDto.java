package cz.neidl.farmapp.model;

import lombok.Data;

import java.util.List;

@Data
public class SensorResponseDto {
    private String sensorName;
    private List<SensorReadingResponseDto> sensorReadings;
}
