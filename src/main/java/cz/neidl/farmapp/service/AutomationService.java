package cz.neidl.farmapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutomationService {
private final EspService espService;

    /**
     * Automates reading data and reading soil humidity watering procedure
     * checks crop humidity for each crop twice a day
     */
    @Scheduled(fixedDelay = 43200*1000 )
private void automate(){
    espService.getAllSensorReadingsSave();
    espService.checkSoilHumidityAll();
    espService.waterLowHumidityPlants();
}
}
