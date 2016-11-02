package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.DatabaseInterface;
import model.WaterPurityReport;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by nharper32 on 10/31/16.
 */
public class HistoryGraphController {

    private MainApplication mainApplication;

    private DatabaseInterface database;

    @FXML
    private LineChart historyGraph;

    @FXML
    private Axis xAxis;

    @FXML
    private Axis yAxis;

    @FXML
    private ComboBox locationCombo;

    @FXML
    private ComboBox yearCombo;

    @FXML
    private ComboBox ppmCombo;

    @FXML
    private Button displayGraphButton;

    private HashSet<String> locationList = new HashSet<>();
    private HashSet<String> yearList = new HashSet<>();


    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        this.database = mainApplication.getDatabaseConn();
        fillLocationList();
        ppmCombo.getItems().addAll("Virus", "Contaminant");
    }

    @FXML
    public void initialize() {
        xAxis.setLabel("Months");
    }

    public void fillLocationList() {
        locationCombo.getItems().clear();
        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String location = purityReport.getLat() + ", " + purityReport.getLong();
            locationList.add(location);
            //System.out.println(i);
        }
        locationCombo.getItems().addAll(locationList);
    }

    private void fillYearList(String location) {
        yearCombo.getItems().clear();
        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String tempLocal = purityReport.getLat() + ", " + purityReport.getLong();
            /*System.out.println(tempLocal);
            System.out.println(purityReport);
            System.out.println("location: " + location);*/
            if (tempLocal.equals(location)) {
                System.out.println("inside fi");
                String date = purityReport.getDate();
                String year = date.substring(0,4);
                yearList.add(year);
                System.out.println(year);
            }
        }
        yearCombo.getItems().addAll(yearList);
    }

    @FXML
    private void handleSelectedLocation() {
        String location = (String) locationCombo.getValue();
        System.out.println("callled");
        fillYearList(location);
    }

    @FXML
    private void handleDisplayGraphButton() {
        if (ppmCombo.getSelectionModel().getSelectedItem().equals("Virus")) {
            yAxis.setLabel("Virus PPM");
        } else {
            yAxis.setLabel("Contaminant PPM");
        }
        XYChart.Series series = new XYChart.Series();

        //series.getData().add(new XYChart.Data(1, 23));
        //get all data matching specified criteria

        String locationEntry = (String) locationCombo.getSelectionModel().getSelectedItem();
        String[] locationArr = locationEntry.split("[,]");
        double specifiedLat = Double.parseDouble(locationArr[0]);
        double specifiedLong = Double.parseDouble(locationArr[1]);
        System.out.println("lat: " + specifiedLat + " long: "  + specifiedLong);
        int specifiedYear = Integer.parseInt((String) yearCombo.getSelectionModel().getSelectedItem());

        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            //System.out.println(i);
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            purityReport.getYear();
        }

        historyGraph.getData().add(series);
    }




}
