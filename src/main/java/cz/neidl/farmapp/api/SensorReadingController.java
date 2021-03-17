package cz.neidl.farmapp.api;


import cz.neidl.farmapp.model.SensorReadingResponseDto;
import cz.neidl.farmapp.service.SensorReadingService;
import lombok.RequiredArgsConstructor;
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
