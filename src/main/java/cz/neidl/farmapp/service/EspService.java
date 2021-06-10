package cz.neidl.farmapp.service;

import cz.neidl.farmapp.arduino.ArduinoMovementRequest;
import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import cz.neidl.farmapp.model.SensorReadingResponseDto;

import java.util.List;


public interface EspService {
    void moveToPosition(ArduinoMovementRequest arduinoMovementRequest);
    void home();
    void plantCrop(PlantedCrop cropToPlant);
    List<PlantedCropResponseDto> plantAllCrops();
    void checkSoilHumidity(PlantedCrop cropToCheck);
    List<PlantedCrop> checkSoilHumidityAll();
    void waterPlant(PlantedCrop plantToWater);
    void waterPlants();
    SensorReadingResponseDto getSensorReading(String sensorName);
    void waterLowHumidityPlants();
    String getAllSensorReadings();
    void getAllSensorReadingsSave();
}
