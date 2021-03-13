package com.example.application.views.seedermanager;

import com.example.application.data.model.SeederPositionRequestDto;
import com.example.application.data.model.SeederRequestDto;
import com.example.application.data.model.SeederResponseDto;
import com.example.application.data.service.SeederService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "SeederManager", layout = MainView.class)
@PageTitle("Seeder manager")
@CssImport("./styles/views/cropmanager/cropmanager-view.css")
public class SeederManagerView extends Div {
    private final SeederService seederService;

    private TextField seederName = new TextField("Seeder name");
    private TextField positionX = new TextField("Position X");
    private TextField positionY = new TextField("Position Y");
    private TextField positionZ = new TextField("Position Z");

    private Button save = new Button("save");


    Grid<SeederResponseDto> seederGrid = new Grid<>(SeederResponseDto.class);

    public SeederManagerView(SeederService seederService) {
        this.seederService = seederService;
        Div content = new Div(seederGrid);
        content.addClassName("content");
        setSizeFull();
        content.setSizeFull();
        configureGrid();
        updateGrid();

        add(content, configureFieldsAndButtons());
    }

    private VerticalLayout configureFieldsAndButtons() {
        positionX.setPlaceholder("X");
        positionY.setPlaceholder("Y");
        positionZ.setPlaceholder("Z");


        save.addClickListener(click -> {
            SeederRequestDto seederRequestDto = new SeederRequestDto();
            SeederPositionRequestDto seederPositionRequestDto = new SeederPositionRequestDto();

            seederPositionRequestDto.setPositionX(positionX.getValue());
            seederPositionRequestDto.setPositionY(positionY.getValue());
            seederPositionRequestDto.setPositionZ(positionZ.getValue());

            seederRequestDto.setName(seederName.getValue());
            seederRequestDto.setSeederPosition(seederPositionRequestDto);

            seederService.saveSeeder(seederRequestDto);
            updateGrid();
        });

        return new VerticalLayout(seederName, positionX, positionY, positionZ, save);
    }

    private void configureGrid() {
        seederGrid.addClassName("contact-grid");
        seederGrid.setSizeFull();
//        seederGrid.setColumns("sensorName", "createdAt", "updatedAt"); //configure the grid
    }

    private void updateGrid() {
        seederGrid.setItems(seederService.findAllSeeders());
    }
}

