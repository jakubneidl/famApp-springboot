package com.example.application.data.arduino;

import lombok.Data;

@Data
public class ArduinoEnvironmentRequest extends ArduinoMovementRequest{
    private String request;
}
