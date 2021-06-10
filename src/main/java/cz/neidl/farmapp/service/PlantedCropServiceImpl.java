package cz.neidl.farmapp.service;

import cz.neidl.farmapp.domain.Crop;
import cz.neidl.farmapp.domain.Harvest;
import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.exception.CropAlreadyPlantedInThisPosition;
import cz.neidl.farmapp.exception.CropNotFoundException;
import cz.neidl.farmapp.exception.HarvestNotFoundException;
import cz.neidl.farmapp.mapper.CropMapper;
import cz.neidl.farmapp.mapper.PlantedCropMapper;
import cz.neidl.farmapp.mapper.PlantedCropPositionMapper;
import cz.neidl.farmapp.mapper.SensorReadingMapper;
import cz.neidl.farmapp.model.PlantedCropRequestDto;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import cz.neidl.farmapp.model.SensorReadingResponseDto;
import cz.neidl.farmapp.repository.CropRepository;
import cz.neidl.farmapp.repository.HarvestRepository;
import cz.neidl.farmapp.repository.PlantedCropPositionRepository;
import cz.neidl.farmapp.repository.PlantedCropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantedCropServiceImpl implements PlantedCropService {
    private final PlantedCropRepository plantedCropRepository;
    private final CropRepository cropRepository;
    private final PlantedCropPositionRepository plantedCropPositionRepository;
    private final HarvestRepository harvestRepository;
    private final PlantedCropMapper plantedCropMapper;
    private final CropMapper cropMapper;
    private final PlantedCropPositionMapper plantedCropPositionMapper;
    private final SensorReadingMapper sensorReadingMapper;

    @Override
    public List<PlantedCropResponseDto> getAllCropsInHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("Cannot find harvest with id " + id));
        return harvest.getHarvestedCrops()
                .stream()
                .map(plantedCropMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets all crops that have been planted since day 1
     * @return list of all planted crops
     */
    @Override
    public List<PlantedCropResponseDto> getAllPlantedCrops() {
        return plantedCropRepository.findAll()
                .stream()
                .map(plantedCropMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates new planted crop
     * @param cropToPlant
     * @return
     */
    @Override
    public PlantedCropResponseDto createPlantCrop(PlantedCropRequestDto cropToPlant) {
        return plantedCropMapper.mapToDto(plantedCropRepository.save(plantedCropMapper.mapToDomain(cropToPlant)));
    }

    /**
     * Deletes planted crop based on its id
     * @param cropId
     */
    @Override
    public void deletePlantedCrop(Long cropId) {
        PlantedCrop plantedCrop = plantedCropRepository.findById(cropId)
                .orElseThrow(() -> new CropNotFoundException("Cannot find this crop"));
        plantedCropRepository.delete(plantedCrop);
    }

    /**
     * Gets list of soil humidity readings for the crop based in its id
     * @param id
     * @return
     */
    @Override
    public List<SensorReadingResponseDto> getPlantedCropHumidity(Long id) {
        PlantedCrop plantedCrop = plantedCropRepository.findById(id)
                .orElseThrow(() -> new CropNotFoundException("Planted crop not found"));
        return plantedCrop.getSoilHumidityReadings()
                .stream()
                .map(sensorReadingMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates new planted crop and saves it
     * @param cropToPlant
     * @return
     */
    @Override
    @Transactional
    public PlantedCropResponseDto plantCrop(PlantedCropRequestDto cropToPlant) {
                Crop crop = cropRepository.findByName(cropToPlant.getCrop().getName())
                .orElseThrow(() -> new CropNotFoundException("Crop not found"));
        PlantedCrop plantedCrop = new PlantedCrop();
        plantedCrop.setCrop(crop);
        plantedCrop.setPlanted(false);
        plantedCrop.setHarvest(harvestRepository.findById(cropToPlant.getHarvestId())
                .orElseThrow(()-> new HarvestNotFoundException("Cannot save plantedCrop Without a harvest id")));
        plantedCrop.setPlantedCropPosition(plantedCropPositionMapper.mapToDomain(cropToPlant.getPlantedCropPosition()));
        return plantedCropMapper.mapToDto(plantedCropRepository.save(plantedCrop));

//        Crop crop = cropRepository.findByName(cropToPlant.getCrop().getName())
//                .orElseThrow(() -> new CropNotFoundException("Crop not found"));
//        PlantedCrop plantedCrop = new PlantedCrop();
//        plantedCrop.setCrop(crop);
//        plantedCrop.setPlanted(false);
//        plantedCrop.setPlantedCropPosition(plantedCropPositionMapper.mapToDomain(cropToPlant.getPlantedCropPosition()));
//
//        int positionX = Integer.valueOf(cropToPlant.getPlantedCropPosition().getPositionX());
//        int positionY = Integer.valueOf(cropToPlant.getPlantedCropPosition().getPositionY());
//
//        int spacing = Integer.valueOf(crop.getSpacing());
//        Integer xMax = positionX + spacing;
//        Integer xMin = positionX - spacing;
//        Integer yMax = positionY + spacing;
//        Integer yMin = positionY - spacing;
//
//        if (true) {
//            System.out.println(plantedCropPositionRepository.findAllCropsNearPositionY(yMax.toString(), yMin.toString()).size());
//            System.out.println(plantedCropPositionRepository.findAllCropsNearPositionX(xMax.toString(), xMin.toString()).size());
//            return plantedCropMapper.mapToDto(plantedCropRepository.save(plantedCrop));
//
//        } else {
//            throw new CropAlreadyPlantedInThisPosition("This crop needs more space");
//        }
    }
}
