package com.example.application.data.repository;


import com.example.application.data.domain.SensorReading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorReadingRepository extends CrudRepository<SensorReading, Long> {
    List<SensorReading> findAll();
}
