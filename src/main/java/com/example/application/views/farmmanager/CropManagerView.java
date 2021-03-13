package com.example.application.views.farmmanager;

import com.example.application.data.domain.Crop;
import com.example.application.data.domain.PlantedCrop;
import com.example.application.data.exception.CropNotFoundException;
import com.example.application.data.model.*;
import com.example.application.data.service.CropService;
import com.example.application.data.service.EspService;
import com.example.application.data.service.PlantedCropService;
import com.example.application.data.service.SeederService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "cropManager", layout = MainView.class)
@PageTitle("Crop manager")
@CssImport("./styles/views/cropmanager/cropmanager-view.css")
public class CropManagerView extends Div {
    private CropService cropService;
    private EspService espService;
    private SeederService seederService;
    private PlantedCropService plantedCropService;

    private TextField filterByNameFiled = new TextField();

    private TextField cropName = new TextField("Crop Name");
    private TextField cropInfo = new TextField("Crop Info");
    private TextField cropSpacing = new TextField("Crop Spacing");
    private TextField idealSoilHumidity = new TextField("Ideal Soil Humidity");
    private TextField seederName = new TextField("Seeder Name");

    private TextField positionX = new TextField("Position X");
    private TextField positionY = new TextField("Position Y");
    private TextField positionZ = new TextField("Position Z");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button plant = new Button("Plant");
//    Button plant = new Button("plant");


    //    Button plantCropButton = new Button("Plant crop");
    Grid<CropResponseDto> gridCrops = new Grid<>(CropResponseDto.class);

    ComboBox<SeederResponseDto> seederCombobox = new ComboBox<>();


//    private final CropManagerForm cropManagerForm;

    public CropManagerView(CropService cropService, EspService espService, SeederService seederService, PlantedCropService plantedCropService) {
        addClassName("list-view");
        this.cropService = cropService;
        this.espService = espService;
        this.plantedCropService = plantedCropService;
        setSizeFull();
        configureGrid();
        configureFilter();
        Div content = new Div(gridCrops);
        content.addClassName("content");
        content.setSizeFull();

        seederCombobox.setItems(seederService.findAllSeeders());
        seederCombobox.setItemLabelGenerator(SeederResponseDto::getName);

        add(filterByNameFiled, content, new HorizontalLayout(createCropFieldsLayout(), positionX, positionY, positionZ));
        updateGrid();
    }

    private void configureFilter() {
        filterByNameFiled.setPlaceholder("Filter by name . . .");
        filterByNameFiled.setClearButtonVisible(true);
        filterByNameFiled.setValueChangeMode(ValueChangeMode.LAZY);
        filterByNameFiled.addValueChangeListener(e -> updateGrid());
    }


//    private Component createComponents() {
//        plantCropButton.addClickListener(click -> espService.plantCrop());
//
////        CropRequestDto cropRequestDto = new CropRequestDto();
////        cropRequestDto.setName(cropNameField.getValue());
////        cropRequestDto.setInfo(cropInfoField.getValue());
////        cropRequestDto.setSpacing(Integer.valueOf(cropSpacingField.getValue()));
//
////        addCropButton.addClickListener(click -> cropService.saveCrop(cropNameField.getValue()));
//        return new VerticalLayout(cropNameField, plantCropButton);
//    }

    private void configureGrid() {
        gridCrops.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridCrops.addClassName("crop-grid");
        gridCrops.setSizeFull();
        gridCrops.setColumns("name", "info", "spacing");
        gridCrops.addColumn(cropResponseDto -> {
                    SeederResponseDto seederResponseDto = cropResponseDto.getSeeder();
                    return seederResponseDto == null ? "-" : seederResponseDto.getName();
                }
        ).setHeader("Seeder name");
    }

    private void updateGrid() {
        gridCrops.setItems(cropService.getAllCrops(filterByNameFiled.getValue()));
    }

    private VerticalLayout createCropFieldsLayout() {
        return new VerticalLayout(cropName, cropInfo, cropSpacing, seederName, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        plant.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        plant.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> {
            saveCrop();
            updateGrid();
        });

        delete.addClickListener(click -> {
            deleteCrop();
            updateGrid();
        });

        plant.addClickListener(click -> {
            plantCrop();
        });


        gridCrops.addSelectionListener(s -> {
            CropResponseDto cropResponseDto = s.getAllSelectedItems().stream().findFirst()
                    .orElseThrow(() -> new CropNotFoundException("Could not find crop"));
            cropName.setValue(cropResponseDto.getName());
            cropInfo.setValue(cropResponseDto.getInfo());
            cropSpacing.setValue(cropResponseDto.getSpacing().toString());
            seederName.setValue(cropResponseDto.getSeeder().getName());
            Notification.show(cropResponseDto.getName() + " selected");
        });

        return new HorizontalLayout(save, delete, plant);
    }

    private void saveCrop() {

        cropService.saveCropFromVaadin(cropName.getValue(), cropInfo.getValue(), cropSpacing.getValue(), seederName.getValue());
    }

    private void deleteCrop() {
        cropService.deleteCropByName(cropName.getValue());
    }

    private void plantCrop() {
        PlantedCropPositionRequestDto plantedCropPositionRequestDto = new PlantedCropPositionRequestDto()
                .setPositionX(positionX.getValue())
                .setPositionY(positionY.getValue())
                .setPositionZ(positionZ.getValue());
        CropRequestDto cropRequestDto = new CropRequestDto()
                .setName(cropName.getValue());
        PlantedCropRequestDto plantedCropRequestDto = new PlantedCropRequestDto()
                .setPlantedCropPosition(plantedCropPositionRequestDto)
                .setCrop(cropRequestDto)
                .setPlanted(true);
        plantedCropService.plantCrop(plantedCropRequestDto);
//        plantedCropService.plantCropFromVaaddin(cropName.getValue(), positionX.getValue(), positionY.getValue(), positionZ.getValue());
        Notification.show("crop: " + cropName.getValue() + " " + positionX.getValue() + " planted to position " + positionX.getValue() + " " + positionY.getValue() + " " + positionZ.getValue());
    }

}
