package com.example.application.api;

import com.example.application.arduino.ArduinoMovementRequest;
import com.example.application.domain.PlantedCrop;
import com.example.application.service.EspService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EspController {
    private final EspService espService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/espPlant")
    public List<PlantedCrop> plantAllCrops(){
        return espService.plantAllCrops();
    }

    @PostMapping (path = "/espMovement")
    public void moveToPositionXYZ(@RequestBody ArduinoMovementRequest arduinoMovementRequest){
        espService.moveToPosition(arduinoMovementRequest);
    }

    @GetMapping(path = "/espHome")
    public void home(){
        espService.home();
    }

    @PostMapping(path = "/soilHumidtyAll")
    public List<PlantedCrop> checkSoilHumidityAll(){
        return espService.checkSoilHumidityAll();
    }
}
