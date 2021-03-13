package com.example.application.data.repository;

import com.example.application.data.domain.PlantedCrop;
import com.example.application.data.domain.PlantedCropPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantedCropRepository extends CrudRepository<PlantedCrop, Long> {
    List<PlantedCrop> findAll();
    List<PlantedCrop> findAllByPlantedCropPosition(PlantedCropPosition plantedCropPosition);
}
