package cz.neidl.farmapp.model;

import lombok.Data;

import java.util.List;

@Data
public class SensorRequestDto {
    private String sensorName;
    private List<SensorReadingRequestDto> sensorReadings;
}
