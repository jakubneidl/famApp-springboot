package cz.neidl.farmapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Sensor extends AbstractEntity{
    private String sensorName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id")
    private List<SensorReading> sensorReadings;
}
