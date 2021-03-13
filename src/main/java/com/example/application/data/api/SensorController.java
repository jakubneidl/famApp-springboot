package com.example.application.data.api;


import com.example.application.data.domain.Sensor;
import com.example.application.data.model.SensorRequestDto;
import com.example.application.data.model.SensorResponseDto;
import com.example.application.data.service.SensorReadingService;
import com.example.application.data.service.SensorService;
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


    @GetMapping(path = "/all")
    public List<SensorResponseDto> findAllSensors() {
        return sensorService.findAllSensors();
    }

    @GetMapping(path = "/sensor/{sensorName}")
    public SensorResponseDto findSensorInfo(@PathVariable("sensorName") String sensorName) {
        return sensorService.findByName(sensorName);
    }

    @GetMapping(path = "/test")
    public String test() {
        return "test";
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
