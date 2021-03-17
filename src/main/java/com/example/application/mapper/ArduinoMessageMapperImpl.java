package com.example.application.mapper;

import com.example.application.arduino.ArduinoMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ArduinoMessageMapperImpl {
    public static ArduinoMessage map(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
            );
            return mapper.readValue(jsonString, ArduinoMessage.class);
        } catch (IOException ex) {
            System.err.println("Unable to parse string to Forecast: "
                    + ex.getMessage());
            return null;
        }
    }
}

