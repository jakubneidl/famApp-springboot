package cz.neidl.farmapp.service;

import cz.neidl.farmapp.domain.Crop;
import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.domain.PlantedCropPosition;
import cz.neidl.farmapp.exception.CropAlreadyPlantedInThisPosition;
import cz.neidl.farmapp.exception.CropNotFoundException;
import cz.neidl.farmapp.exception.PositionNotFoundException;
import cz.neidl.farmapp.mapper.CropMapper;
import cz.neidl.farmapp.mapper.PlantedCropMapper;
import cz.neidl.farmapp.mapper.PlantedCropPositionMapper;
import cz.neidl.farmapp.model.PlantedCropPositionRequestDto;
import cz.neidl.farmapp.model.PlantedCropRequestDto;
import cz.neidl.farmapp.model.PlantedCropResponseDto;
import cz.neidl.farmapp.repository.CropRepository;
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

    private final PlantedCropMapper plantedCropMapper;
    private final CropMapper cropMapper;
    private final PlantedCropPositionMapper plantedCropPositionMapper;

    @Override
    public List<PlantedCropResponseDto> getAllPlantedCrops() {
        return plantedCropRepository.findAll()
                .stream()
                .map(plantedCropMapper::mapToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public PlantedCropResponseDto plantCrop(CropRequestDto cropToPlant) {
//        return plantedCropMapper.mapToDto(plantedCropRepository.save(new PlantedCrop(cropMapper.mapToDomain(cropToPlant))));
//    }

    @Override
    public PlantedCropResponseDto createPlantCrop(PlantedCropRequestDto cropToPlant) {
        return plantedCropMapper.mapToDto(plantedCropRepository.save(plantedCropMapper.mapToDomain(cropToPlant)));
    }

    @Override
    public void plantCropVaaddin(String value, String x, String y, String z) {
        Crop crop = cropRepository.findByName(value).stream().findFirst()
                .orElseThrow(() -> new CropNotFoundException("Crop not found, can't plant crop"));
        PlantedCropPosition plantedCropPosition = new PlantedCropPosition()
                .setPositionX(x)
                .setPositionY(y)
                .setPositionZ(z);

        PlantedCrop cropToPlant = new PlantedCrop()
                .setCrop(crop)
                .setPlantedCropPosition(plantedCropPosition)
                .setPlanted(true);
        plantedCropRepository.save(cropToPlant);

    }

    @Override
    public void plantCropFromVaaddin(String cropName, String positionX, String positionY, String positionZ) {
        Crop crop = cropRepository.findByName(cropName).stream().findFirst()
                .orElseThrow(() -> new CropNotFoundException("Cannot plant crop, crop not found"));

        PlantedCropPosition plantedCropPosition = new PlantedCropPosition();
        plantedCropPosition.setPositionX(positionX);
        plantedCropPosition.setPositionY(positionY);
        plantedCropPosition.setPositionZ(positionZ);

        PlantedCrop plantedCrop = new PlantedCrop();
        plantedCrop.setCrop(crop);
        plantedCrop.setPlantedCropPosition(plantedCropPosition);
        plantedCrop.setPlanted(true);
        plantedCropRepository.save(plantedCrop);
    }

    @Override
    public void deleteFromVaadin(PlantedCropPositionRequestDto plantedCropPositionRequestDto) {

        PlantedCrop plantedCrop = plantedCropRepository.findAllByPlantedCropPosition(plantedCropPositionMapper.mapToDomain(plantedCropPositionRequestDto))
                .stream()
                .findFirst()
                .orElseThrow(()-> new PositionNotFoundException("Nothing found on that position"));
        plantedCropRepository.delete(plantedCrop);
    }

    @Override
    @Transactional
    public PlantedCropResponseDto plantCrop(PlantedCropRequestDto cropToPlant) {
        Crop crop = cropRepository.findByName(cropToPlant.getCrop().getName())
                .orElseThrow(() -> new CropNotFoundException("Crop not found"));
        PlantedCrop plantedCrop = new PlantedCrop();
        plantedCrop.setCrop(crop);
        plantedCrop.setPlanted(false);
        plantedCrop.setPlantedCropPosition(plantedCropPositionMapper.mapToDomain(cropToPlant.getPlantedCropPosition()));

        int positionX = Integer.valueOf(cropToPlant.getPlantedCropPosition().getPositionX());
        int positionY = Integer.valueOf(cropToPlant.getPlantedCropPosition().getPositionY());

        int spacing = Integer.valueOf(crop.getSpacing());
        Integer xMax = positionX + spacing;
        Integer xMin = positionX - spacing;
        Integer yMax = positionY + spacing;
        Integer yMin = positionY - spacing;
//
//        Integer spacing = crop.getSpacing();
//        Integer xMax = Integer.parseInt(positionX) + spacing;
//        Integer xMin = Integer.parseInt(positionX) - spacing;
//        Integer yMax = Integer.parseInt(positionY) + spacing;
//        Integer yMin = Integer.parseInt(positionY) - spacing;


//          fix this later
//        if((plantedCropPositionRepository.findAllCropsNearPositionY(yMax.toString(), yMin.toString()).isEmpty() && plantedCropPositionRepository.findAllCropsNearPositionX(xMax.toString(), xMin.toString()).isEmpty()))
        if (true) {
            System.out.println(plantedCropPositionRepository.findAllCropsNearPositionY(yMax.toString(), yMin.toString()).size());
            System.out.println(plantedCropPositionRepository.findAllCropsNearPositionX(xMax.toString(), xMin.toString()).size());
            return plantedCropMapper.mapToDto(plantedCropRepository.save(plantedCrop));

        } else {
            throw new CropAlreadyPlantedInThisPosition("This crop needs more space");
        }
//        return plantedCropMapper.mapToDto(plantedCropRepository.save(plantedCrop));


    }


}
