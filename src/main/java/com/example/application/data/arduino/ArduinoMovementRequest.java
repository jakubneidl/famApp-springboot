package com.example.application.data.arduino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArduinoMovementRequest extends ArduinoMessage{
    private String positionX;
    private String positionY;
    private String positionZ;
}
