package cz.neidl.farmapp.api;

import cz.neidl.farmapp.arduino.ArduinoMovementRequest;
import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import cz.neidl.farmapp.model.SensorReadingResponseDto;
import cz.neidl.farmapp.service.EspService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EspController {
    private final EspService espService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/espPlant/")
    public List<PlantedCropResponseDto> plantAllCrops(){
        return espService.plantAllCrops();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping (path = "/espMovement")
    public void moveToPositionXYZ(@RequestBody ArduinoMovementRequest arduinoMovementRequest){
        espService.moveToPosition(arduinoMovementRequest);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/espHome")
    public void home(){
        espService.home();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/chckSoilHumidtyAll")
    public List<PlantedCrop> checkSoilHumidityAll(@PathVariable Long harvestId){
        return espService.checkSoilHumidityAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/waterAll")
    public String waterAllCrops(){
        espService.waterPlants();
        return "watering complete";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/waterLow")
    public String waterLowHumidityCrops(){
        espService.waterLowHumidityPlants();
        return "watering low humidity crops complete";
    }

    @GetMapping(path = "/getReading/{sensorName}")
    @CrossOrigin(origins = "http://localhost:8080")
    public SensorReadingResponseDto getSoilTemp(@PathVariable String sensorName){

        return espService.getSensorReading(sensorName);
    }

    @GetMapping(path = "/getAllSensorReadings")
    public String getAllSensorReadings(){
        return espService.getAllSensorReadings();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/getAllSensorReadingsSave")
    public void getAllReadings(){
        espService.getAllSensorReadingsSave();
    }

}
