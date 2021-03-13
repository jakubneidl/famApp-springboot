package com.example.application.data.api;

import com.example.application.data.model.CropRequestDto;
import com.example.application.data.model.CropResponseDto;
import com.example.application.data.service.CropService;
import lombok.RequiredArgsConstructor;
import org.atmosphere.config.service.Post;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CropController {
    private final CropService cropService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/crops")
    public List<CropResponseDto> getAllCrops(){
        return cropService.getAllCrops();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/crops")
    public CropResponseDto saveCrop(@Valid @RequestBody CropRequestDto cropRequestDto){
        return cropService.saveCrop(cropRequestDto);
    }

    @GetMapping(path = "/crops/{cropName}")
    public CropResponseDto getCropByName(@PathVariable String cropName){
        return cropService.getCropByName(cropName);
    }

}
