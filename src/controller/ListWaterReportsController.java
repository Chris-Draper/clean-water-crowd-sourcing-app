package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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


    public void clearList() {
        listItems.clear();
    }

    public void populateList() {

        int startReport = mainApplication.getDatabaseConn().getMinReportNum();
        int endReport = mainApplication.getDatabaseConn().getMaxReportNum();

        for (int i = startReport; i <= endReport; i++) {
            listItems.add(i);
            listView.setItems(listItems);
        }

        reportDisplayCounter = endReport - startReport;
    }


    @FXML
    public void handleMouseClicked() {

        WaterSourceReport waterSourceReportData =
                mainApplication.getDatabaseConn().getReportInfo((int) listView.getSelectionModel().getSelectedItem());

        if (waterSourceReportData == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Error downloading report from database.", ButtonType.OK);
            alert.showAndWait();

        } else {

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
