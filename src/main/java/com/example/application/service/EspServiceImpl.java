package com.example.application.service;

import com.example.application.arduino.ArduinoEnvironmentRequest;
import com.example.application.arduino.ArduinoMessage;
import com.example.application.arduino.ArduinoMovementRequest;
import com.example.application.arduino.ArduinoPlantRequest;
import com.example.application.configuration.RestTemplateClient;
import com.example.application.domain.PlantedCrop;
import com.example.application.domain.SensorReading;
import com.example.application.exception.CropCannotBePlantedException;
import com.example.application.repository.CropRepository;
import com.example.application.repository.PlantedCropRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EspServiceImpl implements EspService {
    private final CropRepository cropRepository;
    private final PlantedCropRepository plantedCropRepository;
    private final RestTemplateClient restTemplateClient;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
       return builder.build();
    }

    @Override
    public List<PlantedCrop> plantAllCrops() {
        log.info("method: plantAllCrops called");
        List<PlantedCrop> all = plantedCropRepository.findAll();
        all.stream()
                .forEach(plantedCrop -> {
                    plantCrop(plantedCrop);
                });
        return all;
    }


    @Transactional
    public void plantCrop(PlantedCrop cropToPlant) {
        log.info("method: Plant crop called");
        if (cropToPlant.isPlanted()) {
            return;
        } else {
            final String uri = "http://192.168.0.121/movement";
//            RestTemplate restTemplate = new RestTemplate();
//Construct the message
            ArduinoPlantRequest arduinoPlantRequest = new ArduinoPlantRequest();
            arduinoPlantRequest.setType("plant");
            arduinoPlantRequest.setSeederPositionX(cropToPlant.getCrop().getSeeder().getSeederPosition().getPositionX());
            arduinoPlantRequest.setSeederPositionY(cropToPlant.getCrop().getSeeder().getSeederPosition().getPositionY());
            arduinoPlantRequest.setSeederPositionZ(cropToPlant.getCrop().getSeeder().getSeederPosition().getPositionZ());

            arduinoPlantRequest.setPositionX(cropToPlant.getPlantedCropPosition().getPositionX());
            arduinoPlantRequest.setPositionY(cropToPlant.getPlantedCropPosition().getPositionY());
            arduinoPlantRequest.setPositionZ(cropToPlant.getPlantedCropPosition().getPositionZ());

            ArduinoMessage arduinoResponse = restTemplateClient.restTemplate().postForObject(uri, arduinoPlantRequest, ArduinoMessage.class);


            if (!arduinoResponse.getType().isEmpty()) {
                cropToPlant.setPlanted(true);
                plantedCropRepository.save(cropToPlant);
            } else
                throw new CropCannotBePlantedException("Arduino not responding correctly, crop cannot be planted :" + arduinoResponse.getType());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }


        }
    }

    @Override
    public void home() {
        log.info("method: home");
        final String uri = "http://192.168.0.121/home";
//        RestTemplate restTemplate = new RestTemplate();

        restTemplateClient.restTemplate().postForEntity(uri, "test", String.class);
    }

    @Override
    public void moveToPosition(ArduinoMovementRequest arduinoMovementRequest) {
        log.info("method: moveToPosition called");
        ArduinoMovementRequest moveToThisPositon = new ArduinoMovementRequest();
        arduinoMovementRequest.setType("movement");

        final String uri = "http://192.168.0.121/movement";
//        RestTemplate restTemplate = new RestTemplate();

        String s = restTemplateClient.restTemplate().postForObject(uri, arduinoMovementRequest, String.class);
    }

    @Override
    public List<PlantedCrop> checkSoilHumidityAll() {
        List<PlantedCrop> all = plantedCropRepository.findAll();
        all.stream()
                .forEach(plantedCrop -> {
                    checkSoilHumidity(plantedCrop);
                });
        return all;
    }

    @Transactional
    public void checkSoilHumidity(PlantedCrop cropToCheck) {
        final String uri = "http://192.168.0.121/movement";
//        RestTemplate restTemplate = new RestTemplate();

        ArduinoEnvironmentRequest arduinoEnvironmentRequest = new ArduinoEnvironmentRequest();
        arduinoEnvironmentRequest.setType("movement");
        arduinoEnvironmentRequest.setRequest("soil");

        arduinoEnvironmentRequest.setPositionX(cropToCheck.getPlantedCropPosition().getPositionX());
        arduinoEnvironmentRequest.setPositionY(cropToCheck.getPlantedCropPosition().getPositionY());
        arduinoEnvironmentRequest.setPositionZ(cropToCheck.getPlantedCropPosition().getPositionZ());

        ArduinoMessage arduinoResponse = restTemplateClient.restTemplate().postForObject(uri, arduinoEnvironmentRequest, ArduinoMessage.class);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        if (!arduinoResponse.getType().isEmpty()) {
            List<SensorReading> soilHumidityReadings = cropToCheck.getSoilHumidityReadings();
            soilHumidityReadings.add(getSoilReading(arduinoEnvironmentRequest));
            cropToCheck.setSoilHumidityReadings(soilHumidityReadings);
            plantedCropRepository.save(cropToCheck);
        } else
            throw new CropCannotBePlantedException("Arduino not responding correctly, soil cannot be checked :" + arduinoResponse.getType());


//        return cropToCheck;
    }
    @Transactional
    public SensorReading getSoilReading(ArduinoEnvironmentRequest arduinoEnvironmentRequest) {
        final String uri = "http://192.168.0.121/movement";
        arduinoEnvironmentRequest.setType("environment");
//        RestTemplate restTemplate = new RestTemplate();

        SensorReading sensorReading = restTemplateClient.restTemplate().postForObject(uri, arduinoEnvironmentRequest, SensorReading.class);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return sensorReading;
    }


    public void waterPlants() {
    }
}
