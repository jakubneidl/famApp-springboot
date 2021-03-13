package com.example.application.views.control;

import com.example.application.data.service.EspService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "movementControl", layout = MainView.class)
@PageTitle("Movement control")
@CssImport("./styles/views/farmcontrol/farmcontrol-view.css")
public class MovementControlView extends Div {

//    Button forward = new Button();
//    Button backward = new Button();
//    Button left = new Button();
//    Button right = new Button();
    Button confirm = new Button("Confirm");
    Button home = new Button("Home");
    TextField moveToPositionX = new TextField("X axis: ");
    TextField moveToPositionY = new TextField("Y axis: ");

    private EspService espService;

    public MovementControlView(EspService espService){
        this.espService = espService;

        add(createButtonLayout());
    }


    private Component createButtonLayout(){

        confirm.addClickShortcut(Key.ENTER);
//        confirm.addClickListener(click ->  espService.moveToPosition(moveToPositionX.getValue(),moveToPositionY.getValue()));
        home.addClickListener(click -> espService.home());
        return new VerticalLayout(moveToPositionX,moveToPositionY, confirm, home);
    }

    private void configureButton(){
//        forward.setText("Forward");
//        backward.setText("Backward");
//        left.setText("Left");
//        right.setText("Right");

    }
}
