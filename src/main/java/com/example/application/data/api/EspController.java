package com.example.application.data.api;

import com.example.application.data.arduino.ArduinoEnvironmentRequest;
import com.example.application.data.arduino.ArduinoMovementRequest;
import com.example.application.data.domain.PlantedCrop;
import com.example.application.data.service.EspService;
import lombok.RequiredArgsConstructor;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Post;
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
