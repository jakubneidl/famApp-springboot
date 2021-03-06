package cz.neidl.farmapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PlantedCrop extends AbstractEntity{
    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "crop_id")
    private Crop crop;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private PlantedCropPosition plantedCropPosition;
    private boolean planted;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "soil_humidity_id")
    private List<SensorReading> soilHumidityReadings;
    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "seeder_id")
    private Seeder seeder;
    @ManyToOne
    @JoinColumn(name = "harvest_id")
    Harvest harvest;
}
