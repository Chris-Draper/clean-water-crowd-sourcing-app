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

    @FXML
    private VBox listPurityReportVBox;

    @FXML
    private ListView listView;
    private ObservableList listItems = FXCollections.observableArrayList();
    private TextArea textArea;
    private static int reportDisplayCounter = 0;

    private MainApplication mainApplication;

    @FXML
    private void initialize() {
        ArrayList<WaterPurityReport> waterPurityReports = WaterPurityReportController.getWaterPurityReportList();

        listItems = FXCollections.observableArrayList();
        System.out.println("Size is " + waterPurityReports.size());
        for (int i = 0; i < waterPurityReports.size(); i++) {
            listItems.add(waterPurityReports.get(i).getReportNum()); //change later, fill with water source report objects
            reportDisplayCounter = waterPurityReports.size();
        } //see if you can directly insert the waterSourceReports into the ListView<> parameter
        listView = new ListView<>(listItems);
        listView.setPrefHeight(490);
        listPurityReportVBox.getChildren().addAll(listView);
        // rootLayout.setCenter(listWaterReportVBox);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                listView.setPrefHeight(230);
                if (textArea != null) {
                    listPurityReportVBox.getChildren().remove(textArea);
                }
                WaterPurityReport waterPurityReportData = null;
                for (int i = 0; i < waterPurityReports.size(); i++) {
                    if (waterPurityReports.get(i).getReportNum().equals(listView.getSelectionModel().getSelectedItem().toString())) {
                        waterPurityReportData = waterPurityReports.get(i);
                    }
                }
                if (waterPurityReportData != null) {
                    textArea = new TextArea(
                            "Report Number: " + waterPurityReportData.getReportNum() + "\n\n" +
                                    "Location Lat: " + waterPurityReportData.getLat() + "\n" +
                                    "Location Long: " + waterPurityReportData.getLong() + "\n" +
                                    "Date of report: " + waterPurityReportData.getDate() + "\n" +
                                    "Time of report: " + waterPurityReportData.getTime() + "\n" +
                                    "Reported By: " + waterPurityReportData.getReporterName() + "\n" +
                                    "Report Virus : " + waterPurityReportData.getVirusPPM() + "\n" +
                                    "Report contaminant : " + waterPurityReportData.getContamPPM() +"\n" +
                                    "Water Condition: " + waterPurityReportData.getCondition()
                    );
                    textArea.setWrapText(true);
                    textArea.setPrefHeight(260);
                    listPurityReportVBox.getChildren().addAll(textArea);
                }
            }
        });
    }

    public void setMainApplication(MainApplication main) {
        this.mainApplication = mainApplication;
    }
 }
