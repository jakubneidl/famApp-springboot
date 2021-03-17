package com.example.application.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SensorReading extends AbstractEntity {
    private String reading;
    private String value;
}
