package cz.neidl.farmapp.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Sensor extends AbstractEntity{
    private String sensorName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_id")
    private Set<SensorReading> sensorReadingSet;
}
