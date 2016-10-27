package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.WaterPurityReport;
import model.WaterSourceReport;
import sun.applet.Main;

import java.util.ArrayList;

/**
 * Created by nharper32 on 10/24/16.
 */
public class ListPurityReportsController {

    private MainApplication mainApplication;

    @FXML
    private VBox listPurityReportVBox;

    @FXML
    private ListView reportList;

    @FXML
    private TextArea textArea;

    private ObservableList listItems = FXCollections.observableArrayList();

    private ArrayList<WaterPurityReport> waterPurityReports = WaterPurityReportController.getWaterPurityReportList();

    private int reportDisplayCounter = 0;

    @FXML
    private void initialize() {
        populateList();
    }

    public void clearList() {
        listItems.clear();
    }
    public void populateList() {
        for (int i = 0; i < waterPurityReports.size(); i++) {
            listItems.add(waterPurityReports.get(i).getReportNum());
            reportDisplayCounter = waterPurityReports.size();
            reportList.setItems(listItems);
        }
    }

    @FXML
    public void handleMouseClicked() {
        WaterPurityReport waterPurityReportData = null;
        for (int i = 0; i < waterPurityReports.size(); i++) {
            if (waterPurityReports.get(i).getReportNum().equals(reportList.getSelectionModel().getSelectedItem().toString())) {
                waterPurityReportData = waterPurityReports.get(i);
            }
        }
        if (waterPurityReportData != null) {
            textArea.setText(
                    "Report Number: " + waterPurityReportData.getReportNum() + "\n\n" +
                            "Location Lat: " + waterPurityReportData.getLat() + "\n" +
                            "Location Long: " + waterPurityReportData.getLong() + "\n" +
                            "Date of report: " + waterPurityReportData.getDate() + "\n" +
                            "Time of report: " + waterPurityReportData.getTime() + "\n" +
                            "Reported By: " + waterPurityReportData.getReporterName() + "\n" +
                            "Report Virus : " + waterPurityReportData.getVirusPPM() + "\n" +
                            "Report contaminant : " + waterPurityReportData.getContamPPM() + "\n" +
                            "Water Condition: " + waterPurityReportData.getCondition()
            );
            textArea.setWrapText(true);
        }

    }

    public void setMainApplication(MainApplication main) {
        this.mainApplication = main;
    }
 }
