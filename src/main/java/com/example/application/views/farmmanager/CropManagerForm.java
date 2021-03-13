//package com.example.application.views.farmmanager;
//
//import com.example.application.data.model.CropResponseDto;
//import com.example.application.data.model.SeederResponseDto;
//import com.vaadin.flow.component.Key;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.Binder;
//
//import java.util.List;
//
//public class CropManagerForm extends FormLayout {
//    TextField cropName = new TextField("Crop Name");
//    TextField cropInfo = new TextField("Crop info");
//    TextField cropSpacing = new TextField("Crop spacing");
//
//    ComboBox<SeederResponseDto> seeder = new ComboBox<>("Seeder");
//
//    Button save = new Button("Save");
//    Button delete = new Button("Delete");
//    Button close = new Button("Cancel");
//
//    Binder<CropResponseDto> binder = new BeanValidationBinder<>(CropResponseDto.class);
////    private Object CropResponseDto;
//
//    public CropManagerForm(List<SeederResponseDto> seederResponseDtoList) {
//        addClassName("contact-form");
//
//        binder.bindInstanceFields(this);
//        seeder.setItems(seederResponseDtoList);
//        seeder.setItemLabelGenerator(SeederResponseDto::getName);
//
//
////        binder.bindInstanceFields(this);
////        company.setItems(seederResponseDtoList);
////        company.setItemLabelGenerator(SeederResponseDto::getName);
//
//        add(cropName,
//                cropInfo,
//                cropSpacing,
//                seeder,
//                createButtonsLayout());
//    }
//
//    public void setCrop(CropResponseDto cropResponseDto) {
//        binder.setBean(cropResponseDto);
//    }
//
//    private HorizontalLayout createButtonsLayout() {
//        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
//        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//
//        save.addClickShortcut(Key.ENTER);
//        close.addClickShortcut(Key.ESCAPE);
//
//        return new HorizontalLayout(save, delete, close);
//    }
//
//
//}
