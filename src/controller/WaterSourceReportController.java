package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.WaterSourceReport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Controls the flow of information between the WaterSourceReport Model and
 * the view
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
    private ComboBox<WaterSourceReport.WaterCondition> reportWaterCondition;

    @FXML
    private ComboBox<WaterSourceReport.WaterType> reportWaterType;

    private static Integer reportSystemCount;

    private static ArrayList<WaterSourceReport> waterSourceReportList;


    public void setMainApplication(MainApplication mainApplication1) {
        this.mainApplication = mainApplication1;

        int userID = mainApplication.getAuthenticatedUser().getID();
        String[] infoFields = mainApplication.getDatabaseConn().getProfileInfo(userID);

        if (infoFields[0] != null) {
            reporterName.setText(infoFields[0]);
        } else {
            reporterName.setText(mainApplication.getAuthenticatedUser().getUsername());
        }

        reportSystemCount = mainApplication.getDatabaseConn().getMaxSourceReportNum() + 1;
        reportNumLabel.setText(reportSystemCount.toString());
    }
    @FXML
    private void initialize() {
        this.setReportWaterConditionData();
        this.setReportWaterType();
        reportDate.setText(this.getDate());
        reportTime.setText(this.getTime());
    }

    private void setReportWaterConditionData() {
        reportWaterCondition.getItems().clear();
        reportWaterCondition.getItems().addAll(
                WaterSourceReport.WaterCondition.values()
        );
    }

    private void setReportWaterType() {
        reportWaterType.getItems().clear();
        reportWaterType.getItems().addAll(
                WaterSourceReport.WaterType.values()
        );
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
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
                    Integer.parseInt(reportNumLabel.getText()), reporterName.getText(),
                    reportLat, reportLong, reportWaterType.getValue(),
                    reportWaterCondition.getValue()
            ));

            mainApplication.getDatabaseConn().submitWaterSourceReport(
                    reportDate.getText(), reportTime.getText(),
                    reportNumLabel.getText(), reporterName.getText(),
                    reportLat, reportLong, reportWaterType.getValue().name(),
                    reportWaterCondition.getValue().name()
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your water source report was submitted successfully");
            alert.showAndWait();
            reportSystemCount++;
            reportDate.setText(this.getDate());
            reportTime.setText(this.getTime());
            reportNumLabel.setText(reportSystemCount.toString());
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

        ArrayList<WaterSourceReport> curReports;



        return waterSourceReportList;
    }

    public static void initWaterReportList() {
        waterSourceReportList = new ArrayList<WaterSourceReport>();
    }

    public static void makeWaterSrcReportDummyData() {

    }


}
