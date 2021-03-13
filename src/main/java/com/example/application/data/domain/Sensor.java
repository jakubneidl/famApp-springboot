package com.example.application.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Sensor extends AbstractEntity{
    private String sensorName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_id")
    private Set<SensorReading> sensorReadingSet;
}
