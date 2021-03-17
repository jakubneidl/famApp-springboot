package cz.neidl.farmapp.model;

import cz.neidl.farmapp.domain.Sensor;
import lombok.Data;

@Data
public class SensorReadingResponseDto {
    private String reading;
    private Sensor sensor;
}
