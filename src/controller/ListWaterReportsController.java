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
import model.WaterSourceReport;
import java.util.ArrayList;

/**
 * Controls the list view of the water reports
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

    private ArrayList<WaterSourceReport> waterSourceReports =
            WaterSourceReportController.getWaterSourceReportList();

    /**
     * Clears the list of water purity reports
     */
    public void clearList() {
        listItems.clear();
    }

    /**
     * Populates the lists of water purity reports with data from the database
     */
    public void populateList() {

        int startReport = mainApplication.getDatabaseConn()
                .getMinSourceReportNum();
        int endReport = mainApplication.getDatabaseConn()
                .getMaxSourceReportNum();

        for (int i = startReport; i <= endReport; i++) {
            listItems.add(i);
            listView.setItems(listItems);
        }

        reportDisplayCounter = endReport - startReport;
    }

    /**
     * Loads and formats the data when the list purity reports button
     * is clicked
     */
    @FXML
    public void handleMouseClicked() {

        WaterSourceReport waterSourceReportData =
                mainApplication.getDatabaseConn().getSourceReportInfo(
                        (int) listView.getSelectionModel().getSelectedItem());

        if (waterSourceReportData == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Error downloading report from database.", ButtonType.OK);
            alert.showAndWait();

        } else {

            textArea.setText(
                    "Report Number: " + waterSourceReportData.getReportNum()
                            + "\n\n" + "Location Lat: "
                            + waterSourceReportData.getLat()
                            + "\n" + "Location Long: "
                            + waterSourceReportData.getLong()
                            + "\n" + "Date of report: "
                            + waterSourceReportData.getDate()
                            + "\n" + "Time of report: "
                            + waterSourceReportData.getTime()
                            + "\n" + "Reported By: "
                            + waterSourceReportData.getReporterName()
                            + "\n" + "Source Type: "
                            + waterSourceReportData.getSourceType()
                            + "\n" + "Water Condition: "
                            + waterSourceReportData.getCondition()
            );
            textArea.setWrapText(true);
        }
    }

    /**
     * Loads the main application
     *
     * @param mainApplication the variable that references the main application
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
