package cz.neidl.farmapp.api;

import cz.neidl.farmapp.arduino.ArduinoMovementRequest;
import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.service.EspService;
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
