package cz.neidl.farmapp.service;

import cz.neidl.farmapp.model.CropRequestDto;
import cz.neidl.farmapp.model.CropResponseDto;

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
