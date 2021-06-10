package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class SensorReadingRequestDto {
    private String reading;
    private String value;
}
