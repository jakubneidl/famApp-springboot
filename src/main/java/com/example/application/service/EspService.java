package com.example.application.service;

import com.example.application.arduino.ArduinoMovementRequest;
import com.example.application.domain.PlantedCrop;

import java.util.List;


public interface EspService {
    void moveToPosition(ArduinoMovementRequest arduinoMovementRequest);

    void home();

    void plantCrop(PlantedCrop cropToPlant);

    List<PlantedCrop> plantAllCrops();

    void checkSoilHumidity(PlantedCrop cropToCheck);

    List<PlantedCrop> checkSoilHumidityAll();
}
