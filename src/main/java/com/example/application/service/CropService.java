package com.example.application.service;

import com.example.application.model.CropRequestDto;
import com.example.application.model.CropResponseDto;

import java.util.List;

public interface CropService {
    CropResponseDto saveCrop(CropRequestDto cropRequestDto);
    CropResponseDto saveCrop(String cropName);
    List<CropResponseDto> getAllCrops();
    CropResponseDto getCropByName(String cropName);
    List<CropResponseDto> getAllCrops(String cropName);

    void deleteCropByName(String cropName);

    void saveCropFromVaadin(String name, String info, String spacing, String seederName);
}
