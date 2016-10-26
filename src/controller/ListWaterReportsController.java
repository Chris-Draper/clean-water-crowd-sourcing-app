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
import model.WaterPurityReport;
import model.WaterSourceReport;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sbuck on 10/17/2016.
 */
public class ListWaterReportsController {

    private MainApplication mainApplication;

    @FXML
    private VBox listWaterReportVBox;

    @FXML
    private ListView listView;

    @FXML
    private TextArea textArea;

    private ObservableList listItems = FXCollections.observableArrayList();

    private static int reportDisplayCounter = 0;

    private ArrayList<WaterSourceReport> waterSourceReports = WaterSourceReportController.getWaterSourceReportList();


    @FXML
    private void initialize() {
        populateList();
    }

    public void clearList() {
        listItems.clear();
    }

    public void populateList() {
        for (int i = 0; i < waterSourceReports.size(); i++) {
            listItems.add(waterSourceReports.get(i).getReportNum());
            reportDisplayCounter = waterSourceReports.size();
            listView.setItems(listItems);
        }
    }


    @FXML
    public void handleMouseClicked() {
        WaterSourceReport waterSourceReportData = null;
        for (int i = 0; i < waterSourceReports.size(); i++) {
            if (waterSourceReports.get(i).getReportNum().equals(listView.getSelectionModel().getSelectedItem().toString())) {
                waterSourceReportData = waterSourceReports.get(i);
            }
        }
        if (waterSourceReportData != null) {
            System.out.println("hi");
            textArea.setText(
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
        }

    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
