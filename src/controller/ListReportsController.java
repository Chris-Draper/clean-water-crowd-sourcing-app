package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.WaterSourceReport;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sbuck on 10/17/2016.
 */
public class ListReportsController {

    @FXML
    private VBox listWaterReportVBox;

    @FXML
    private ListView listView;
    private ObservableList listItems = FXCollections.observableArrayList();
    private TextArea textArea;
    private static int reportDisplayCounter = 0;

    private MainApplication mainApplication;

    @FXML
    private void initialize() {
        ArrayList<WaterSourceReport> waterSourceReports = WaterSourceReportController.getWaterSourceReportList();

        listItems = FXCollections.observableArrayList();
        for (int i = 0; i < waterSourceReports.size(); i++) {
            listItems.add(waterSourceReports.get(i).getReportNum()); //change later, fill with water source report objects
            reportDisplayCounter = waterSourceReports.size();
        } //see if you can directly insert the waterSourceReports into the ListView<> parameter
        listView = new ListView<>(listItems);
        listView.setPrefHeight(490);
        listWaterReportVBox.getChildren().addAll(listView);
        // rootLayout.setCenter(listWaterReportVBox);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                listView.setPrefHeight(230);
                if (textArea != null) {
                    listWaterReportVBox.getChildren().remove(textArea);
                }
                WaterSourceReport waterSourceReportData = null;
                for (int i = 0; i < waterSourceReports.size(); i++) {
                    if (waterSourceReports.get(i).getReportNum().equals(listView.getSelectionModel().getSelectedItem().toString())) {
                        waterSourceReportData = waterSourceReports.get(i);
                    }
                }
                if (waterSourceReportData != null) {
                    textArea = new TextArea(
                            "Report Number: " + waterSourceReportData.getReportNum() + "\n\n" +
                                    "Location Lat: " + waterSourceReportData.getLat() + "\n" +
                                    "Location Long: " + waterSourceReportData.getLong() + "\n" +
                                    "Date of report: " + waterSourceReportData.getDate() + "\n" +
                                    "Time of report: " + waterSourceReportData.getTime() + "\n" +
                                    "Reported By: " + waterSourceReportData.getReporterName() + "\n" +
                                    "Source Type: " + waterSourceReportData.getSourceType() + "\n" +
                                    "Water Condition: " + waterSourceReportData.getCondition()
                    );
                    textArea.setWrapText(true);
                    textArea.setPrefHeight(260);
                    listWaterReportVBox.getChildren().addAll(textArea);
                }
            }
        });
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
