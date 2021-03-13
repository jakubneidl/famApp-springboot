package com.example.application.data.arduino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArduinoPlantRequest extends ArduinoMovementRequest{
    private String seederPositionX;
    private String seederPositionY;
    private String seederPositionZ;
}
