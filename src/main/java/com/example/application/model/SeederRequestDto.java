package com.example.application.model;

import lombok.Data;

@Data
public class SeederRequestDto {
    private String name;
    private SeederPositionRequestDto seederPosition;
}
