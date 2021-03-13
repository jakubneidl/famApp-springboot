package com.example.application.data.model;

import lombok.Data;

import java.util.Set;

@Data
public class SeederRequestDto {
    private String name;
    private SeederPositionRequestDto seederPosition;
}
