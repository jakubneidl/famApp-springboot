package cz.neidl.farmapp.service;

import cz.neidl.farmapp.arduino.ArduinoMovementRequest;
import cz.neidl.farmapp.domain.PlantedCrop;

import java.util.List;


public interface EspService {
    void moveToPosition(ArduinoMovementRequest arduinoMovementRequest);

    void home();

    void plantCrop(PlantedCrop cropToPlant);

    List<PlantedCrop> plantAllCrops();

    void checkSoilHumidity(PlantedCrop cropToCheck);

    List<PlantedCrop> checkSoilHumidityAll();
}
