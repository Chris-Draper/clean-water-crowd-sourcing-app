package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.WaterSourceReport;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Chris on 10/11/2016.
 */
public class WaterSourceReportController {

    @FXML
    private TextField reporterName;

    @FXML
    private TextField reportSourceLocation;

    @FXML
    private Label reportNumber;

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

    private Integer reportSystemCount = 1001;

    @FXML
    private void initialize() {
        this.setReportWaterConditionData();
        this.setReportWaterType();
        reportDate.setText(this.getDate());
        reportTime.setText(this.getTime());
        reportNumber.setText(reportSystemCount.toString());
    }

    private void setReportWaterConditionData() {
        reportWaterCondition.getItems().clear();
        reportWaterCondition.getItems().addAll(
                "Waste", "Treatable Clear", "Treatable Muddy", "Potable"
        );
    }

    private void setReportWaterType() {
        reportWaterType.getItems().clear();
        reportWaterType.getItems().addAll(
                "Bottled", "Well", "Stream", "Lake", "Spring", "Other"
        );
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your water source report was submitted successfully");
        alert.showAndWait();
        reportSystemCount++;
        reportDate.setText(this.getDate());
        reportTime.setText(this.getTime());
        reportNumber.setText(reportSystemCount.toString());
        reporterName.clear();
        reportSourceLocation.clear();
        this.setReportWaterConditionData();
        this.setReportWaterType();
    }

}
