package cz.neidl.farmapp.arduino;

import lombok.Data;

@Data
public class ArduinoEnvironmentRequest extends ArduinoMovementRequest{
    private String request;
}
