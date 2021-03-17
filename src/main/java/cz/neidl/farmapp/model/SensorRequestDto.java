package cz.neidl.farmapp.model;

import lombok.Data;

import java.util.Set;

@Data
public class SensorRequestDto {
    private String sensorName;
    private Set<SensorReadingRequestDto> sensorReadingSet;
}
