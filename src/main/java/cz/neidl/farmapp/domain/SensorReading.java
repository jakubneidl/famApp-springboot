package cz.neidl.farmapp.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class SensorReading extends AbstractEntity {
    private String reading;
    private String value;
}
