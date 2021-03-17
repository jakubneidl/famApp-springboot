package cz.neidl.farmapp.api;

import cz.neidl.farmapp.model.CropRequestDto;
import cz.neidl.farmapp.model.CropResponseDto;
import cz.neidl.farmapp.service.CropService;
import lombok.RequiredArgsConstructor;
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
