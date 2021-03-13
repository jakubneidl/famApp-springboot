package com.example.application.data.api;


import com.example.application.data.model.SensorReadingResponseDto;
import com.example.application.data.service.SensorReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class SensorReadingController {
    private final SensorReadingService sensorReadingService;


    @GetMapping(path = "/sensorReadings")
    public List<SensorReadingResponseDto> sensorReadings(){
        return sensorReadingService.findAllReadings();
    }
}
