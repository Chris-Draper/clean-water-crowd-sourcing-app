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

import java.util.*;

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
    private HashSet<WaterPurityReport> matchList = new HashSet<>();
    private XYChart.Series series;

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
                //System.out.println("inside fi");
                String date = purityReport.getDate();
                //System.out.println(date);
                String year = date.substring(0,4);
                yearList.add(year);
                //System.out.println(year);
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
        boolean contam = ppmCombo.getSelectionModel().getSelectedItem().equals("Contaminant");
        yAxis.setLabel("Contaminant/Virus PPM");

        String locationEntry = (String) locationCombo.getSelectionModel().getSelectedItem();
        String[] locationArr = locationEntry.split("[,]");
        double specifiedLat = Double.parseDouble(locationArr[0]);
        double specifiedLong = Double.parseDouble(locationArr[1]);
        //System.out.println("lat: " + specifiedLat + " long: "  + specifiedLong);
        int specifiedYear = Integer.parseInt((String) yearCombo.getSelectionModel().getSelectedItem());

        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            //System.out.println(i);
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String date = purityReport.getDate();
            int tempYear = Integer.parseInt(date.substring(0,4));
            double tempLat = purityReport.getLat();
            double tempLong = purityReport.getLong();
            if (specifiedLat == tempLat && specifiedLong == tempLong && specifiedYear == tempYear) {
                matchList.add(purityReport);
            }
        }
        HashMap<Integer, WaterPurityReport> monthList = new HashMap<>();
        for (WaterPurityReport purityReport : matchList) {
            //System.out.println(purityReport.getMonth()+ 1);
            int currMonth = purityReport.getMonth()+ 1;
            switch (currMonth) {
                case 1:  monthList.put(currMonth, purityReport);
                    break;
                case 2:  monthList.put(currMonth, purityReport);
                    break;
                case 3:  monthList.put(currMonth, purityReport);
                    break;
                case 4:  monthList.put(currMonth, purityReport);
                    break;
                case 5:  monthList.put(currMonth, purityReport);
                    break;
                case 6:  monthList.put(currMonth, purityReport);
                    break;
                case 7:  monthList.put(currMonth, purityReport);
                    break;
                case 8:  monthList.put(currMonth, purityReport);
                    break;
                case 9:  monthList.put(currMonth, purityReport);
                    break;
                case 10: monthList.put(currMonth, purityReport);
                    break;
                case 11: monthList.put(currMonth, purityReport);
                    break;
                case 12: monthList.put(currMonth, purityReport);
                    break;
                default: monthList.put(currMonth, purityReport);
                    break;
            }
        }
        if (series != null) historyGraph.getData().removeAll(series);
        series = new XYChart.Series();
        List templist = new ArrayList<>(monthList.keySet());
        Collections.sort(templist);

        for (Object key: templist) {
            System.out.println(monthList.get(key));
            WaterPurityReport temp = monthList.get(key);
            if (contam) {
                if (temp != null) series.getData().add(new XYChart.Data(key, temp.getContamPPM()));
            } else {
                if (temp != null) series.getData().add(new XYChart.Data(key, temp.getVirusPPM()));
            }

        }
        if (contam) {
            series.setName("Location's (" + specifiedLat + ", " + specifiedLong + ") Contaminant PPM in Year " + specifiedYear);
        } else {
            series.setName("Location's (" + specifiedLat + ", " + specifiedLong + ") Virus PPM in Year " + specifiedYear);
        }


        historyGraph.getData().add(series);
    }

}
