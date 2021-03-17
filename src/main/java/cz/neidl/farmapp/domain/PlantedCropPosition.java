package cz.neidl.farmapp.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class PlantedCropPosition extends AbstractEntity{
    private String positionX;
    private String positionY;
    private String positionZ;
//    @OneToOne
//    private PlantedCrop plantedCrop;
}
