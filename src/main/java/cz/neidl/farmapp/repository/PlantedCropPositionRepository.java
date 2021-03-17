package cz.neidl.farmapp.repository;

import cz.neidl.farmapp.domain.PlantedCropPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantedCropPositionRepository extends CrudRepository<PlantedCropPosition, Long> {
//                                                           300              310    300             290
    @Query(value = "SELECT p FROM PlantedCropPosition p WHERE p.positionY <= :xMax and p.positionY >= :xMin")
    List<PlantedCropPosition> findAllCropsNearPositionY(
            @Param("xMax") String xMax,
            @Param("xMin") String xMin);

    @Query(value = "SELECT p FROM PlantedCropPosition p WHERE p.positionX <= :xMax and p.positionX >= :xMin")
    List<PlantedCropPosition> findAllCropsNearPositionX(
            @Param("xMax") String xMax,
            @Param("xMin") String xMin);

    @Query(value = "SELECT p FROM PlantedCropPosition p WHERE p.positionX <= :xMax and p.positionX >= :xMin and p.positionY <= :yMax and p.positionY >= :yMin")
    List<PlantedCropPosition> findAllCropsNearPositionXY(
            @Param("xMax") String xMax,
            @Param("xMin") String xMin,
            @Param("yMax") String yMax,
            @Param("yMin") String yMin);


    @Query(value = "SELECT p FROM PlantedCropPosition p WHERE p.positionX = :xa AND p.positionY = :y")
    List<PlantedCropPosition> findAllCropsAtThisPosition(
            @Param("xa") String x,
            @Param("y") String y);




}
