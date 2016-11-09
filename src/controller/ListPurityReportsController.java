package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.WaterPurityReport;

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

    public void clearList() {
        listItems.clear();
    }

    public void populateList() {

        int startReport = mainApplication.getDatabaseConn().getMinPurityReportNum();
        int endReport = mainApplication.getDatabaseConn().getMaxPurityReportNum();

        for (int i = startReport; i <= endReport; i++) {
            listItems.add(i);
            reportList.setItems(listItems);
        }

        reportDisplayCounter = endReport - startReport;

    }

    @FXML
    public void handleMouseClicked() {


        WaterPurityReport waterPurityReportData =
                mainApplication.getDatabaseConn().getPurityReportInfo((int) reportList.getSelectionModel().getSelectedItem());

        if (waterPurityReportData == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Error downloading report from database.", ButtonType.OK);
            alert.showAndWait();

        } else {

            textArea.setText(
                    "Report Number: " + waterPurityReportData.getReportNum() + "\n\n" +
                            "Location Lat: " + waterPurityReportData.getLat() + "\n" +
                            "Location Long: " + waterPurityReportData.getLong() + "\n" +
                            "Date of report: " + waterPurityReportData.getDate() + "\n" +
                            "Time of report: " + waterPurityReportData.getTime() + "\n" +
                            "Reported By: " + waterPurityReportData.getReporterName() + "\n" +
                            "Report Virus : " + waterPurityReportData.getVirusPPM() + "\n" +
                            "Report contaminant : " + waterPurityReportData.getContamPPM() + "\n" +
                            "Water Condition: " + waterPurityReportData.getCondition().getDescription()
            );
            textArea.setWrapText(true);
        }

    }

    public void setMainApplication(MainApplication main) {
        this.mainApplication = main;
    }
 }
