package com.example.application.data.service;

import com.example.application.data.model.CropRequestDto;
import com.example.application.data.model.CropResponseDto;

import java.util.List;
import java.util.Set;

public interface CropService {
    CropResponseDto saveCrop(CropRequestDto cropRequestDto);
    CropResponseDto saveCrop(String cropName);
    List<CropResponseDto> getAllCrops();
    CropResponseDto getCropByName(String cropName);
    List<CropResponseDto> getAllCrops(String cropName);

    void deleteCropByName(String cropName);

    void saveCropFromVaadin(String name, String info, String spacing, String seederName);
}
