package cz.neidl.farmapp.api;


import cz.neidl.farmapp.domain.Sensor;
import cz.neidl.farmapp.model.SensorRequestDto;
import cz.neidl.farmapp.model.SensorResponseDto;
import cz.neidl.farmapp.service.SensorReadingService;
import cz.neidl.farmapp.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final SensorReadingService sensorReadingService;

    @GetMapping(path = "/allDomain")
    public List<Sensor> findAllDomainSensors() {
        return sensorService.findAllDomainSensors();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/allSensors")
    public List<SensorResponseDto> findAllSensors() {
        return sensorService.findAllSensors();
    }

    @GetMapping(path = "/sensor/{sensorName}")
    public SensorResponseDto findSensorInfo(@PathVariable("sensorName") String sensorName) {
        return sensorService.findByName(sensorName);
    }
    @PostMapping(path = "/newSensor")
    public SensorResponseDto saveSensor(@RequestBody SensorRequestDto sensor) {
        return sensorService.saveSensor(sensor);
    }
    @PostMapping(path = "/newData")
    public SensorResponseDto saveSensorData(@RequestBody SensorRequestDto sensorReading){
        return sensorService.updateData(sensorReading);
    }
}
