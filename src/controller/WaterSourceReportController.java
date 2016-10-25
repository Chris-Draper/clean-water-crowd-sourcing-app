package controller;

import fxapp.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.WaterSourceReport;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Chris on 10/11/2016.
 */
public class WaterSourceReportController {

    private MainApplication mainApplication;

    @FXML
    private GridPane addressGridPane;

    @FXML
    private Label reporterName;

    @FXML
    private TextField reportSourceLat;

    @FXML
    private TextField reportSourceLong;

    @FXML
    private Label reportNumLabel;

    @FXML
    private Label reportDate;

    @FXML
    private Label reportTime;

    @FXML
    private ComboBox reportWaterCondition;

    @FXML
    private ComboBox reportWaterType;

    @FXML
    private Button submitWaterSourceReportButton;

    private static Integer reportSystemCount = 1001;

    private static ArrayList<WaterSourceReport> waterSourceReportList;


    public void setMainApplication(MainApplication mainApplication1) {
        this.mainApplication = mainApplication1;
        reporterName.setText(mainApplication.getAuthenticatedUser().getUsername());
    }
    @FXML
    private void initialize() {
        this.setReportWaterConditionData();
        this.setReportWaterType();
        reportDate.setText(this.getDate());
        reportTime.setText(this.getTime());
        reportNumLabel.setText(reportSystemCount.toString());

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
    private void handleSubmitWaterSourceReport() {
        if (isCompleted()) {
            double reportLat = Double.parseDouble(reportSourceLat.getText());
            double reportLong = Double.parseDouble(reportSourceLong.getText());

            waterSourceReportList.add(new WaterSourceReport(
                    reportDate.getText(), reportTime.getText(),
                    reportNumLabel.getText(), reporterName.getText(),
                    reportLat, reportLong, reportWaterCondition.getValue(),
                    reportWaterType.getValue()
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your water source report was submitted successfully");
            alert.showAndWait();
            reportSystemCount++;
            reportDate.setText(this.getDate());
            reportTime.setText(this.getTime());
            reportNumLabel.setText(reportSystemCount.toString());
            reporterName.setText(mainApplication.getAuthenticatedUser().getFullName());
            reportSourceLat.clear();
            reportSourceLong.clear();
            this.setReportWaterConditionData();
            this.setReportWaterType();
        }
    }

    private boolean isCompleted() {
        //ensure all text boxes are filled in
        boolean ans = true;
        if (reportSourceLat.getText().equals("") || reportSourceLong.getText().equals("")
                || reportWaterType.getValue() == null
                || reportWaterCondition.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
            ans = false;
        }
        return ans;
    }

    public static ArrayList<WaterSourceReport> getWaterSourceReportList() {
        return waterSourceReportList;
    }

    public static void initWaterReportList() {
        waterSourceReportList = new ArrayList<WaterSourceReport>();
    }

    public static void makeWaterSrcReportDummyData() {
        WaterSourceReport report1 = new WaterSourceReport( //top
                "10/12/16", "22:16", "2001", "Chris Draper", 33.78, -84.15,
                "Lake", "Treatable Clear"
        );
        WaterSourceReport report2 = new WaterSourceReport( //right
                "10/15/16", "22:16", "2002", "Don Draper", 34.03, -84.40,
                "Well", "Potable"
        );
        WaterSourceReport report3 = new WaterSourceReport( //bottom
                "10/17/16", "22:16", "2003", "Cynthia Draper", 33.78, -84.65,
                "Spring", "Treatable Muddy"
        );
        WaterSourceReport report4 = new WaterSourceReport( //left
                "9/12/16", "22:16", "2004", "Zach Draper", 33.53, -84.40,
                "Bottled", "Waste"
        );
        waterSourceReportList.add(report1);
        waterSourceReportList.add(report2);
        waterSourceReportList.add(report3);
        waterSourceReportList.add(report4);
    }


}
