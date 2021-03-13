package com.example.application.views.farmcontrol;

import com.example.application.data.model.SensorReadingResponseDto;
import com.example.application.data.service.SensorService;
import com.example.application.views.main.MainView;
import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.legend.HorizontalAlign;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.config.xaxis.XAxisType;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "control", layout = MainView.class)
@PageTitle("Farm control")
@CssImport("./styles/views/farmcontrol/farmcontrol-view.css")
public class FarmcontrolView extends Div {


    TextField filterText = new TextField();
    private SensorService sensorService;
    ApexCharts areaChart = new ApexCharts();


    public FarmcontrolView(SensorService sensorService) {
        this.sensorService = sensorService;

        configureChart();
//        configureField();
        add(areaChart);

        setWidth("100%");
    }

    private void configureChart() {
        List<Integer> data = new ArrayList<>();
        data.add(30);
        for (int i = 0; i <10 ; i++) {
            data.add(i);

        }


        int humid = sensorService.findByName("humidity").getSensorReadingSet().size();
        Object[] elements = new Object[humid];
        List<SensorReadingResponseDto> humid1 = sensorService.findByName("humidity").getSensorReadingSet().stream().collect(Collectors.toList());

        for (int i = 0; i <humid ; i++) {
            elements[i] = humid1.get(i).getReading();
        }
        int temp = sensorService.findByName("temp").getSensorReadingSet().size();
        Object[] elements1 = new Object[temp];
        List<SensorReadingResponseDto> temp1 = sensorService.findByName("temp").getSensorReadingSet().stream().collect(Collectors.toList());

        for (int i = 0; i <temp ; i++) {
            elements1[i] = temp1.get(i).getReading();
        }


        Series series = new Series<>("humidity");
        Series series2 = new Series<>("temp");

        series.setData(elements);
        series2.setData(elements1);

        List<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");
        test.add("3");


        areaChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.area)
                        .withZoom(ZoomBuilder.get()
                                .withEnabled(false)
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false)
                        .build())
                .withStroke(StrokeBuilder.get().withCurve(Curve.smooth).build())
                .withSeries(series)
                .withTitle(TitleSubtitleBuilder.get()
                        .withText("Fundamental Analysis of Stocks")
                        .withAlign(Align.left).build())
                .withSubtitle(TitleSubtitleBuilder.get()
                        .withText(filterText.getValue())
                        .withAlign(Align.left).build())
                .withLabels()
                .withXaxis(XAxisBuilder.get()
                        .withType(XAxisType.categories)
                        .build())
                .withYaxis(YAxisBuilder.get()
                        .withOpposite(true)
                        .build())
                .withLegend(LegendBuilder.get().withHorizontalAlign(HorizontalAlign.left).build())
                .build();
    }

    private void configureField() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> configureChart());
    }
//    private String getString()

//private void obtainData(String filterGraph){
//    Set<SensorReadingResponseDto> temp = sensorService.findByName(filterGraph).getSensorReadingSet().forEach(reading -> reading.getReading());
//    temp.stream().forEach(reading -> {
//
//        reading.getReading();
//
//    });
//    }
}

