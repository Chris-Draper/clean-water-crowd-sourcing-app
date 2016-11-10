package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import model.WaterSourceReport;

/**
 * Controls the list view of the water reports
 */
public class ListWaterReportsController {

    private MainApplication mainApplication;

    @FXML
    private ListView listView;

    @FXML private TextArea textArea; //used on line 79

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
                .getMinSourceReportNum();
        int endReport = mainApplication.getDatabaseConn()
                .getMaxSourceReportNum();

        for (int i = startReport; i <= endReport; i++) {
            listItems.add(i);
            listView.setItems(listItems);
        }
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
                    waterSourceReportData.listFormatString()
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
