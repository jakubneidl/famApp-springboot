package com.example.application.arduino;

import lombok.Data;

@Data
public class ArduinoEnvironmentRequest extends ArduinoMovementRequest{
    private String request;
}
