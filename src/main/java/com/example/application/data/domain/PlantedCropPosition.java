package com.example.application.data.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
@Entity
@Data
public class PlantedCropPosition extends AbstractEntity{
    private String positionX;
    private String positionY;
    private String positionZ;
//    @OneToOne
//    private PlantedCrop plantedCrop;
}
