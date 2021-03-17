package cz.neidl.farmapp.model;

import cz.neidl.farmapp.domain.Sensor;
import lombok.Data;

@Data
public class SensorReadingRequestDto {
    private String reading;
    private Sensor sensor;
}
