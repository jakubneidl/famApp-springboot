package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class PositionRequestDto {
    String positionX;
    String positionY;
    String positionZ;
}
