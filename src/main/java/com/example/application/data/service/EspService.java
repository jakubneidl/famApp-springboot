package com.example.application.data.service;

import com.example.application.data.arduino.ArduinoMovementRequest;
import com.example.application.data.domain.Crop;
import com.example.application.data.domain.PlantedCrop;

import java.util.List;


public interface EspService {
    void moveToPosition(ArduinoMovementRequest arduinoMovementRequest);

    void home();

    void plantCrop(PlantedCrop cropToPlant);

    List<PlantedCrop> plantAllCrops();

    void checkSoilHumidity(PlantedCrop cropToCheck);

    List<PlantedCrop> checkSoilHumidityAll();
}
