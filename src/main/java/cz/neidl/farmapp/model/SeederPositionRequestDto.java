package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class SeederPositionRequestDto {
    private String positionX;
    private String positionY;
    private String positionZ;
//    private SeederRequestDto seeder;
}
