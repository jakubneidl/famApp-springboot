package cz.neidl.farmapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Harvest extends AbstractEntity{
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "harvest_id")
    private List<PlantedCrop> harvestedCrops;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "harvest_id")
    private List<Sensor> harvestSensors;
}
