package cz.neidl.farmapp.repository;


import cz.neidl.farmapp.domain.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {
    List<Sensor> findAll();
    Optional<Sensor> findById(Long id);
    Optional<Sensor> findBySensorName(String sensorName);
}
