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

/**
 * The controller that displays the list view of the water purity reports
 */
public class ListPurityReportsController {

    private MainApplication mainApplication;

    @FXML
    private VBox listPurityReportVBox;

    @FXML
    private ListView reportList;

    @FXML
    private TextArea textArea;

    private final ObservableList listItems
            = FXCollections.observableArrayList();

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
                .getMinPurityReportNum();
        int endReport = mainApplication.getDatabaseConn()
                .getMaxPurityReportNum();

        for (int i = startReport; i <= endReport; i++) {
            listItems.add(i);
            reportList.setItems(listItems);
        }
    }

    /**
     * Loads and formats the data when the list purity reports button
     * is clicked
     */
    @FXML
    public void handleMouseClicked() {

        WaterPurityReport waterPurityReportData =
                mainApplication.getDatabaseConn().getPurityReportInfo(
                        (int) reportList.getSelectionModel().getSelectedItem());

        if (waterPurityReportData == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Error downloading report from database.", ButtonType.OK);
            alert.showAndWait();

        } else {

            textArea.setText(
                    waterPurityReportData.listFormatString()
            );
            textArea.setWrapText(true);
        }

    }

    /**
     * Loads the main application
     *
     * @param main the variable that references the main application
     */
    public void setMainApplication(MainApplication main) {
        this.mainApplication = main;
    }
 }
