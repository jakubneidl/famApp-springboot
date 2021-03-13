package com.example.application.data.model;

import lombok.Data;

import java.util.Set;

@Data
public class SeederResponseDto {
    private String name;
    private SeederPositionResponseDto seederPosition;
}
