package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
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
    }

    public void fillLocationList() {
        locationCombo.getItems().clear();
        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String location = purityReport.getLat() + " " + purityReport.getLong();
            locationList.add(location);
            System.out.println(i);
        }
        locationCombo.getItems().addAll(locationList);
    }

    private void fillYearList(String location) {
        yearCombo.getItems().clear();
        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String tempLocal = purityReport.getLat() + " " + purityReport.getLong();
            if (tempLocal.equals(location)) {
                String date = purityReport.getDate();
                String year = date.substring(0,4);
                yearList.add(year);
            }
        }
        yearCombo.getItems().addAll(yearList);
    }

    @FXML
    private void handleSelectedLocation() {
        String location = (String) locationCombo.getValue();
        fillYearList(location);
    }

    @FXML
    private void handleDisplayGraphButton() {

    }




}
