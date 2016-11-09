package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.GenericUser;
import model.WaterPurityReport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sbuck on 10/22/2016.
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

    @FXML
    private GridPane addressGridPane;

    @FXML
    private TextField virusTextField;

    @FXML
    private TextField contTextField;

    @FXML
    private RadioButton safeButton;

    @FXML
    private RadioButton treatableButton;

    @FXML
    private RadioButton unsafeButton;

    @FXML
    private VBox vbox2;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<WaterPurityReport.Condition> conditionComboBox;

    private static Integer reportNum;

    private static ArrayList<WaterPurityReport> waterPurityReportList;

    private GenericUser currentUser;

    public static void initWaterPurityList() {
        waterPurityReportList = new ArrayList<>();
    }

    private Date date;


    @FXML
    private void initialize() {

        //set text for all labels based on person doing this
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateLabel.setText(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText(dateFormat.format(date));
        this.setReportPurityConditionData();

        //set visbility to false for now until we add functionality to input
        //street address and come up with a pin instead of long lat nums
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
                    reportLat, reportLong, conditionComboBox.getValue(), Double.parseDouble(virusTextField.getText()),
                    Double.parseDouble(contTextField.getText())
            ));*/

            waterPurityReportList.add(new WaterPurityReport(
                    date, timeLabel.getText(),
                    reportNum, reporterNameLabel.getText(),
                    reportLat, reportLong, conditionComboBox.getValue(), Double.parseDouble(virusTextField.getText()),
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
            alert.setContentText("Your water Purity report was submitted successfully");
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


    @FXML
    private void handleCancelButton() {

    }

    private boolean isCompleted() {
        //ensure all text boxes are filled in
        boolean ans = true;
        if (latTextField.getText().equals("") || longTextField.getText().equals("")
                || virusTextField.getText().equals("") || contTextField.getText().equals("")
                || conditionComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
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

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    public void setDate(String dateTime) {
        this.date = date;
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    public static ArrayList<WaterPurityReport> getWaterPurityReportList() {
        return waterPurityReportList;
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     *
     * @param mainApplication the reference to the FX Application instance
     */

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        currentUser = this.mainApplication.getAuthenticatedUser();
        reporterNameLabel.setText(currentUser.getUsername());

        reportNum = mainApplication.getDatabaseConn().getMaxPurityReportNum() + 1;
        reportNumLabel.setText(reportNum.toString());
    }

}
