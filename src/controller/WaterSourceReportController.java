package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.WaterSourceReport;

/**
 * Created by Chris on 10/11/2016.
 */
public class WaterSourceReportController {

    @FXML
    private TextField reporterName;

    @FXML
    private TextField reportSourceLocation;

    @FXML
    private TextField reportNumber;

    @FXML
    private TextField reportDate;

    @FXML
    private TextField reportTime;

    @FXML
    private ComboBox reportWaterCondition;

    @FXML
    private ComboBox reportWaterType;

    @FXML
    private Button submitWaterSourceReportButton;

    @FXML
    private void initialize() {
        this.setReportWaterConditionData();
        this.setReportWaterType();
    }

    private void setReportWaterConditionData() {
        reportWaterCondition.getItems().clear();
        reportWaterCondition.getItems().addAll(
                "Waste", "TreatableClear", "TreatableMuddy", "Potable"
        );
    }

    private void setReportWaterType() {
        reportWaterType.getItems().clear();
        reportWaterType.getItems().addAll(
                "Bottled", "Well", "Stream", "Lake", "Spring", "Other"
        );
    }

    @FXML
    private void handleSubmitWaterSourceReport(ActionEvent event) {
        if (event.getSource() == submitWaterSourceReportButton) {
            WaterSourceReport.addReportToList(
                new WaterSourceReport(
                    reportDate.getText(), reportTime.getText(),
                    reportNumber.getText(), reporterName.getText(),
                    reportSourceLocation.getText(), reportWaterCondition.getValue(),
                    reportWaterType.getValue()
                )
            );
        }
    }

}
