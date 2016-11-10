package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.DatabaseInterface;
import model.WaterPurityReport;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

/**
 * Controls the display of the history graph of water contamination
 */
public class HistoryGraphController {

    private MainApplication mainApplication; //called on line 61

    private DatabaseInterface database;

    @FXML
    private LineChart historyGraph;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private ComboBox locationCombo;

    @FXML
    private ComboBox yearCombo;

    @FXML
    private ComboBox ppmCombo;

    @FXML
    private Button displayGraphButton;

    private final Set<String> locationList = new HashSet<>();
    private final Set<String> yearList = new HashSet<>();
    private Set<WaterPurityReport> matchList;
    private XYChart.Series series;

    /**
     * allow for calling back to the mainApplication application code if
     * necessary
     *
     * @param mainApplication the reference to the FX Application instance
     */
    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        this.database = mainApplication.getDatabaseConn();
        fillLocationList();
        ppmCombo.getItems().addAll("Virus", "Contaminant");
    }

    /**
     * This method is called when the fxml view is created
     */
    @FXML
    public void initialize() {
        xAxis.setLabel("Months");
    }

    /**
     * Method for initializing the combo box that lists available locations
     * for history graph
     *
     */
    public void fillLocationList() {
        locationCombo.getItems().clear();
        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String location = purityReport.getLat() + ", " +
                    purityReport.getLong();
            locationList.add(location);
            //System.out.println(i);
        }
        locationCombo.getItems().addAll(locationList);
    }

    /**
     * Helper method for filling year combo box. Called when user
     * selects location
     *
     * @param location location selected by user
     */
    private void fillYearList(String location) {
        yearCombo.getItems().clear();
        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String tempLocal = purityReport.getLat() + ", " +
                    purityReport.getLong();
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
        boolean contaminant = "Contaminant".equals(ppmCombo.getSelectionModel()
                .getSelectedItem());
        yAxis.setLabel("Contaminant/Virus PPM");
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(12);
        String locationEntry = (String) locationCombo.getSelectionModel()
                .getSelectedItem();
        String[] locationArr = locationEntry.split("[,]");
        double specifiedLat = Double.parseDouble(locationArr[0]);
        double specifiedLong = Double.parseDouble(locationArr[1]);
        int specifiedYear = Integer.parseInt((String) yearCombo
                .getSelectionModel().getSelectedItem());

        matchList = new HashSet<>();

        int maxNum = database.getMaxPurityReportNum();
        int minNum = database.getMinPurityReportNum();
        for (int i = minNum; i <= maxNum; i++) {
            WaterPurityReport purityReport = database.getPurityReportInfo(i);
            String date = purityReport.getDate();
            int tempYear = Integer.parseInt(date.substring(0,4));
            double tempLat = purityReport.getLat();
            double tempLong = purityReport.getLong();
            if ((specifiedLat == tempLat) &&
                (specifiedLong == tempLong) &&
                (specifiedYear == tempYear)) {
                    matchList.add(purityReport);
            }
        }
        Map<Integer, WaterPurityReport> monthList = new HashMap<>();
        for (WaterPurityReport purityReport : matchList) {
            int currMonth = purityReport.getMonth()+ 1;
            monthList.put(currMonth, purityReport);
        }
        if (series != null) {
            historyGraph.getData().clear();
        }
        series = new XYChart.Series();
        List templist = new ArrayList<>(monthList.keySet());
        Collections.sort(templist);

        for (Object key: templist) {
            WaterPurityReport temp = monthList.get(key);
            if (contaminant) {
                if (temp != null) {
                    series.getData().add(new XYChart.Data(key,
                            temp.getContamPPM()));
                }
            } else {
                if (temp != null) {
                    series.getData().add(new XYChart.Data(key,
                            temp.getVirusPPM()));
                }
            }
        }
        if (contaminant) {
            series.setName("Location's (" + specifiedLat + ", " + specifiedLong
                    + ") Contaminant PPM in Year " + specifiedYear);
        } else {
            series.setName("Location's (" + specifiedLat + ", " + specifiedLong
                    + ") Virus PPM in Year " + specifiedYear);
        }

        historyGraph.getData().add(series);
    }

}
