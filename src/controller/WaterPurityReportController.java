package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.GenericUser;
import model.WaterPurityReport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Controls the water purity report list view in the main app
 */
public class WaterPurityReportController {

    private MainApplication mainApplication;

    @FXML
    private Label reportNumLabel;

    @FXML
    private Label reporterNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private TextField latTextField;

    @FXML
    private TextField longTextField;

    /*@FXML
    private GridPane addressGridPane; for future use*/

    @FXML
    private TextField virusTextField;

    @FXML
    private TextField contTextField;

    @FXML
    private ComboBox<WaterPurityReport.Condition> conditionComboBox;

    private static Integer reportNum;

    private static List<WaterPurityReport> waterPurityReportList;

    private Date date;

    /**
     * Creates the water purity list that is accessed by the Google Map
     */
    public static void initWaterPurityList() {
        waterPurityReportList = new ArrayList<>();
    }

    @FXML
    private void initialize() {

        //set text for all labels based on person doing this
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateLabel.setText(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText(dateFormat.format(date));
        this.setReportPurityConditionData();

        //set visibility to false for now until we add functionality to input
        //street address and come up with a pin instead of long lat numbers
        //addressGridPane.setVisible(false);
    }

    @FXML
    private void handleSubmitButton() {
        if(isCompleted()) {
            double reportLat = Double.parseDouble(latTextField.getText());
            double reportLong = Double.parseDouble(longTextField.getText());

           /* waterPurityReportList.add(new WaterPurityReport(
                    dateLabel.getText(), timeLabel.getText(),
                    reportNum, reporterNameLabel.getText(),
                    reportLat, reportLong, conditionComboBox.getValue(),
                    Double.parseDouble(virusTextField.getText()),
                    Double.parseDouble(contTextField.getText())
            ));*/

            waterPurityReportList.add(new WaterPurityReport(
                    date, timeLabel.getText(),
                    reportNum, reporterNameLabel.getText(),
                    reportLat, reportLong, conditionComboBox.getValue(),
                    Double.parseDouble(virusTextField.getText()),
                    Double.parseDouble(contTextField.getText())
            ));

            mainApplication.getDatabaseConn().submitWaterPurityReport(
                    dateLabel.getText(), timeLabel.getText(),
                    reportNumLabel.getText(), reporterNameLabel.getText(),
                    reportLat, reportLong, conditionComboBox.getValue().name(),
                    virusTextField.getText(), contTextField.getText()
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your water Purity report was submitted"
                    + " successfully");
            alert.showAndWait();
            reportNum++;
            dateLabel.setText(this.getDate());
            timeLabel.setText(this.getTime());
            reportNumLabel.setText(reportNum.toString());
            latTextField.clear();
            longTextField.clear();
            virusTextField.clear();
            contTextField.clear();

        }
    }

    /*@FXML
    private void handleCancelButton() {
    }*/

    private boolean isCompleted() {
        //ensure all text boxes are filled in
        boolean ans = true;
        if (("").equals(latTextField.getText())
            || (("").equals(longTextField.getText()))
            || (("").equals(virusTextField.getText()))
            || (("").equals(contTextField.getText()))
            || (conditionComboBox.getValue() == null)) {
                Alert alert = new Alert(
                        Alert.AlertType.ERROR,
                        "Please complete all fields",
                        ButtonType.OK
                );
                alert.showAndWait();
                ans = false;
        }
        return ans;
    }

    private void setReportPurityConditionData() {
        conditionComboBox.getItems().clear();
        conditionComboBox.getItems().addAll(
                WaterPurityReport.Condition.values()
        );
    }

    /**
     * Get the date of a water purity report
     * @return returns the dte of aa water purity report
     */
    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    /*This method has not yet been implemented yet because the functionality
    of administrator accounts has not yet been created */
    /**
     * Sets the date of a water purity report
     * @param dateTime - the date of the water purity report
     */
    /*public void setDate(Date dateTime) {
        this.date = dateTime;
    }*/

    /**
     * Sets the time of the water purity report
     * @return the time of the water purity report
     */
    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    /**
     * allow for calling back to the mainApplication application
     * code if necessary
     *
     * @param mainApplication the reference to the FX Application instance
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        GenericUser currentUser = this.mainApplication.getAuthenticatedUser();
        reporterNameLabel.setText(currentUser.getUsername());

        reportNum = mainApplication.getDatabaseConn()
                .getMaxPurityReportNum() + 1;
        reportNumLabel.setText(reportNum.toString());
    }

}
