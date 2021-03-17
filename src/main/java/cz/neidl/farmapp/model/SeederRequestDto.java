package cz.neidl.farmapp.model;

import lombok.Data;

@Data
public class SeederRequestDto {
    private String name;
    private SeederPositionRequestDto seederPosition;
}
