package com.example.application.views.plantedcropmanager;

import com.example.application.data.exception.CropNotFoundException;
import com.example.application.data.model.*;
import com.example.application.data.service.PlantedCropService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "PlantedCropManager", layout = MainView.class)
@PageTitle("Planted crop manager")
@CssImport("./styles/views/cropmanager/cropmanager-view.css")
public class PlantedCropManagerView extends Div {
    private final PlantedCropService plantedCropService;

    Button delete =  new Button("Delete");

    Grid<PlantedCropResponseDto> plantedCropsGrid = new Grid<>(PlantedCropResponseDto.class);


    public PlantedCropManagerView(PlantedCropService plantedCropService){
        this.plantedCropService = plantedCropService;
        setSizeFull();
        configureGrid();
        updateGrid();
        Div content = new Div(plantedCropsGrid);
        deletePlantedCropButtonCofigure();
        content.setSizeFull();
        add(content, delete);
    }
    private void configureGrid(){
        plantedCropsGrid.addClassName("contact-grid");
        plantedCropsGrid.setSizeFull();
        plantedCropsGrid.setColumns();
        plantedCropsGrid.addColumn(plantedCropResponse -> {
                    CropResponseDto crop = plantedCropResponse.getCrop();
                    return crop == null ? "-" : crop.getName();
                }
        ).setHeader("Crop name");
        plantedCropsGrid.addColumn(plantedCropResponse -> {
                    PlantedCropPositionResponseDto cropPositon = plantedCropResponse.getPlantedCropPosition();
                    return cropPositon == null ? "-" : "X : " + cropPositon.getPositionX() + " Y "+ cropPositon.getPositionY() + " Z " + cropPositon.getPositionZ();
                }
        ).setHeader("Position");

    }
    private void updateGrid(){
        plantedCropsGrid.setItems(plantedCropService.getAllPlantedCrops());
    }

    private void deletePlantedCropButtonCofigure(){
        PlantedCropPositionRequestDto plantedCropPositionRequestDto =  new PlantedCropPositionRequestDto();




        plantedCropsGrid.addSelectionListener(c->
                {
                    PlantedCropResponseDto plantedCropResponseDto = c.getFirstSelectedItem().stream().findFirst()
                            .orElseThrow(() -> new CropNotFoundException("Crop not found"));
                     plantedCropPositionRequestDto.setPositionX(plantedCropResponseDto.getPlantedCropPosition().getPositionX());
                     plantedCropPositionRequestDto.setPositionY(plantedCropResponseDto.getPlantedCropPosition().getPositionY());
                     plantedCropPositionRequestDto.setPositionZ(plantedCropResponseDto.getPlantedCropPosition().getPositionZ());
                }
        );

        delete.addClickListener(click-> {
            plantedCropService.deleteFromVaadin(plantedCropPositionRequestDto);
        });
    }

}
