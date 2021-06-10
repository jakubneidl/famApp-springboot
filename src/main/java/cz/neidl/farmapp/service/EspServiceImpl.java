package cz.neidl.farmapp.service;

import cz.neidl.farmapp.arduino.ArduinoEnvironmentRequest;
import cz.neidl.farmapp.arduino.ArduinoMessage;
import cz.neidl.farmapp.arduino.ArduinoMovementRequest;
import cz.neidl.farmapp.arduino.ArduinoPlantRequest;
import cz.neidl.farmapp.configuration.RestTemplateClient;
import cz.neidl.farmapp.domain.Harvest;
import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.domain.Sensor;
import cz.neidl.farmapp.domain.SensorReading;
import cz.neidl.farmapp.exception.CropCannotBePlantedException;
import cz.neidl.farmapp.exception.CropNotFoundException;
import cz.neidl.farmapp.exception.HarvestNotFoundException;
import cz.neidl.farmapp.exception.SensorNotFoundException;
import cz.neidl.farmapp.mapper.PlantedCropMapper;
import cz.neidl.farmapp.mapper.SensorReadingMapper;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import cz.neidl.farmapp.model.SensorReadingResponseDto;
import cz.neidl.farmapp.repository.CropRepository;
import cz.neidl.farmapp.repository.HarvestRepository;
import cz.neidl.farmapp.repository.PlantedCropRepository;
import cz.neidl.farmapp.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EspServiceImpl implements EspService {
    private final CropRepository cropRepository;
    private final PlantedCropRepository plantedCropRepository;
    private final RestTemplateClient restTemplateClient;
    private final SensorRepository sensorRepository;
    private final PlantedCropMapper plantedCropMapper;
    private final SensorReadingMapper sensorReadingMapper;
    private final HarvestRepository harvestRepository;

    private final String postUrl = "http://192.168.0.121/movement";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
       return builder.build();
    }

    /**
     * Method fetches all planted crops that are in the latest harvest
     * @return list of planted crops with updated value planted to true.
     */
    @Override
    public List<PlantedCropResponseDto> plantAllCrops() {
        log.info("method: plantAllCrops called");
        Harvest harvest = harvestRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new HarvestNotFoundException("Cannot plant all crops, no harvest found"));

        List<PlantedCrop> harvestedCrops = harvest.getHarvestedCrops();
        harvestedCrops.forEach(plantedCrop -> {
                    plantCrop(plantedCrop);
                });
        return harvestedCrops
                .stream()
                .map(plantedCropMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Method generates and sends messages to arduino through restTemplate
     * @param cropToPlant object of type PlantedCrop whose data will be used to construct an arduino message
     */
    @Transactional
    public void plantCrop(PlantedCrop cropToPlant) {
        log.info("method: Plant crop called");
        if (cropToPlant.isPlanted()) {
            return;
        } else {
            final String uri = "http://192.168.0.121/movement";

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

    /**
     * Calls endpoint in ESP which invokes arduino homing procedure
     */
    @Override
    public void home() {
        log.info("method: home");
        final String uri = "http://192.168.0.121/home";

        restTemplateClient.restTemplate().postForEntity(uri, "test", String.class);
    }

    /**
     * Sends request to esp to move to specific positions
     * @param arduinoMovementRequest
     */
    @Override
    public void moveToPosition(ArduinoMovementRequest arduinoMovementRequest) {
        log.info("method: moveToPosition called");
        arduinoMovementRequest.setType("movement");

        final String uri = "http://192.168.0.121/movement";

        String s = restTemplateClient.restTemplate().postForObject(uri, arduinoMovementRequest, String.class);
    }

    /**
     * gets list of plantedCrops for the last created harvest and checks their humidity
     * @return list of plantedCrops for the last harvest
     */
    @Override
    public List<PlantedCrop> checkSoilHumidityAll() {
        Harvest harvest = harvestRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new HarvestNotFoundException("Cannot check crops humidity, no harvest found"));
        List<PlantedCrop> harvestedCrops = harvest.getHarvestedCrops();
        harvestedCrops.stream()
                .forEach(plantedCrop -> {
                    checkSoilHumidity(plantedCrop);
                });
        return harvestedCrops;
    }

    /**
     * Gets a sensor reading data from arduino and saves it
     * @param sensorName
     * @return sensorReadingResponseDto
     */
    @Override
    @Transactional
    public SensorReadingResponseDto getSensorReading(String sensorName) {
        ArduinoEnvironmentRequest arduinoEnvironmentRequest = new ArduinoEnvironmentRequest();
        arduinoEnvironmentRequest.setType("environment");
        arduinoEnvironmentRequest.setRequest(sensorName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        SensorReading sensorReading = restTemplateClient.restTemplate()
                .postForObject(postUrl, arduinoEnvironmentRequest, SensorReading.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        if(sensorRepository.findBySensorName(arduinoEnvironmentRequest.getRequest()).isEmpty()){
            Sensor sensor =  new Sensor();
            List<SensorReading> sensorReadingList = new ArrayList<>();
            sensorReadingList.add(sensorReading);
            sensor.setSensorName(arduinoEnvironmentRequest.getRequest());
            sensor.setSensorReadings(sensorReadingList);
            sensorRepository.save(sensor);
        } else{
            Sensor sensor = sensorRepository.findBySensorName(arduinoEnvironmentRequest.getRequest())
                    .orElseThrow(() -> new SensorNotFoundException("Cannot find " + arduinoEnvironmentRequest.getRequest() + " Sensor"));
            sensor.getSensorReadings().add(sensorReading);

            sensorRepository.save(sensor);
        }

        return sensorReadingMapper.mapToDto(sensorReading);
    }

    /**
     * Ivokes soil humidity checking procedure for specific plant
     * @param cropToCheck
     */
    @Transactional
    public void checkSoilHumidity(PlantedCrop cropToCheck) {
        final String uri = "http://192.168.0.121/movement";

        ArduinoEnvironmentRequest arduinoEnvironmentRequest = new ArduinoEnvironmentRequest();
        arduinoEnvironmentRequest.setType("movement");
        arduinoEnvironmentRequest.setRequest("soil_humidity_sensor");
        arduinoEnvironmentRequest.setPositionX(cropToCheck.getPlantedCropPosition().getPositionX());
        arduinoEnvironmentRequest.setPositionY(cropToCheck.getPlantedCropPosition().getPositionY());
        arduinoEnvironmentRequest.setPositionZ(cropToCheck.getPlantedCropPosition().getPositionZ());

        ArduinoMessage arduinoResponse = restTemplateClient.restTemplate()
                .postForObject(uri, arduinoEnvironmentRequest, ArduinoMessage.class);

        try {
            Thread.sleep(1000);
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
    }

    /**
     * Gets sensor reading from arduino
     * @param arduinoEnvironmentRequest
     * @return sensor reading with values read from arduino
     */
    @Transactional
    public SensorReading getSoilReading(ArduinoEnvironmentRequest arduinoEnvironmentRequest) {
        final String uri = "http://192.168.0.121/movement";
        arduinoEnvironmentRequest.setType("environment");

        SensorReading sensorReading = restTemplateClient.restTemplate().postForObject(uri, arduinoEnvironmentRequest, SensorReading.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return sensorReading;
    }

    /**
     * Waters the specific crop
     * @param plantToWater is a crop which is going to be watered
     */
    @Override
    public void waterPlant(PlantedCrop plantToWater) {

        final String uri = "http://192.168.0.121/movement";
        ArduinoEnvironmentRequest arduinoEnvironmentRequest = new ArduinoEnvironmentRequest();
        arduinoEnvironmentRequest.setType("movement");
        arduinoEnvironmentRequest.setRequest("water");

        arduinoEnvironmentRequest.setPositionX(plantToWater.getPlantedCropPosition().getPositionX());
        arduinoEnvironmentRequest.setPositionY(plantToWater.getPlantedCropPosition().getPositionY());
        Integer positionZ = Integer.valueOf(plantToWater.getPlantedCropPosition().getPositionZ()) /2 ;
        arduinoEnvironmentRequest.setPositionZ(positionZ.toString());

        ArduinoMessage arduinoResponse = restTemplateClient.restTemplate().postForObject(uri, arduinoEnvironmentRequest, ArduinoMessage.class);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        if (!arduinoResponse.getType().isEmpty()) {

            arduinoEnvironmentRequest.setType("environment");
            ArduinoMessage arduinoMessage = restTemplateClient.restTemplate().postForObject(uri, arduinoEnvironmentRequest, ArduinoMessage.class);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        } else
            throw new CropCannotBePlantedException("Arduino not responding correctly, task cannot be completed :" + arduinoResponse.getType());


        log.info("Watering finished");

    }

    /**
     * Gets the last harvest and its list of planted crops
     * Invokes watering procedure for plantedCrops in that list.
     */
    @Override
    public void waterPlants() {
        Harvest harvest = harvestRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new HarvestNotFoundException("Cannot water crops, no harvest found"));

        harvest.getHarvestedCrops()
                .stream()
                .forEach(plantedCrop -> {
                    waterPlant(plantedCrop);
                });


    }

    /**
     * Gets list of planted crops for the last harvest and  invokes watering of the crops with humidity set lower then
     * the ideal humidity
     */
    @Override
    public void waterLowHumidityPlants(){
        Harvest harvest = harvestRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new HarvestNotFoundException("Cannot water crops, no harvest found"));

        harvest.getHarvestedCrops()
                .stream()
                .forEach(plantedCrop -> {
                    SensorReading sensorReading = plantedCrop.getSoilHumidityReadings()
                            .stream()
                            .reduce((first, second) -> second)
                            .orElseThrow(() -> new CropNotFoundException("Crop reading not found"));
                    if(Integer.valueOf(sensorReading.getReading()) > Integer.valueOf(plantedCrop.getCrop().getIdealSoilHumidity())){
                        waterPlant(plantedCrop);
                        log.info("soil humidity reading of " + sensorReading.getReading());
                    }else{
                        return ;
                    }
                });
    }

    /**
     * gets all sensor readings from arduino
     * @return sting type. It's a method used for testing
     */
    @Override
    public String getAllSensorReadings() {

        ArduinoEnvironmentRequest arduinoEnvironmentRequest = new ArduinoEnvironmentRequest();
        arduinoEnvironmentRequest.setType("environment");
        arduinoEnvironmentRequest.setRequest("all");

        return restTemplateClient
                .restTemplate()
                .postForObject(postUrl, arduinoEnvironmentRequest, String.class);
    }

    /**
     * Gets data from pre - registered basic set of sensors
     * */
    public void getAllSensorReadingsSave() {
        log.info("getAllSensorReadingsSave called");
        List<String> requests = new ArrayList<>();
        requests.add("soil_temp_sensor");
        requests.add("air_temperature");
        requests.add("air_humidity");
        requests.add("soil_humidity_global");
        requests.stream().forEach(request-> {
            getSensorReading(request);
        });
    }
}
