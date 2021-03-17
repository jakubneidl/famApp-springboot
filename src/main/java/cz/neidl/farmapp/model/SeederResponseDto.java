package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class SeederResponseDto {
    private String name;
    private SeederPositionResponseDto seederPosition;
}
