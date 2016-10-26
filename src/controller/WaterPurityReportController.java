package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.GenericUser;
import model.WaterPurityReport;
import model.WaterSourceReport;

import javax.swing.*;
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
    private ToggleGroup conditionGroup;

    private static Integer reportNum = 2001;

    private static ArrayList<WaterPurityReport> waterPurityReportList;

    private GenericUser currentUser;

    public static void initWaterPurityList() {
        waterPurityReportList = new ArrayList<>();
    }

    private Date date;



    @FXML
    private void initialize() {

        //set text for all labels based on person doing this
        reportNumLabel.setText(reportNum.toString());
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        dateLabel.setText(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("HH:mm");
        timeLabel.setText(dateFormat.format(date));

        conditionGroup = new ToggleGroup();
        safeButton.setToggleGroup(conditionGroup);
        treatableButton.setToggleGroup(conditionGroup);
        unsafeButton.setToggleGroup(conditionGroup);

        //set visbility to false for now until we add functionality to input
        //street address and come up with a pin instead of long lat nums
        //addressGridPane.setVisible(false);
    }

    @FXML
    private void handleSubmitButton() {
        if(isCompleted()) {
            double reportLat = Double.parseDouble(latTextField.getText());
            double reportLong = Double.parseDouble(longTextField.getText());

            waterPurityReportList.add(new WaterPurityReport(
                    reportNum.toString(), reporterNameLabel.getText(),
                    reportLat, reportLong, (WaterPurityReport.Condition) conditionGroup.getSelectedToggle().getUserData(), Double.parseDouble(virusTextField.getText()),
                    Double.parseDouble(contTextField.getText())
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your water Purity report was submitted successfully");
            alert.showAndWait();
            reportNum++;
            dateLabel.setText(this.getDate());
            timeLabel.setText(this.getTime());
            reportNumLabel.setText(reportNum.toString());
            reporterNameLabel.setText(mainApplication.getAuthenticatedUser().getFullName());
            latTextField.clear();
            longTextField.clear();
        }
    }


    @FXML
    private void handleCancelButton() {

    }

    private boolean isCompleted() {
        //ensure all text boxes are filled in
        boolean ans = true;
        System.out.println(conditionGroup.getSelectedToggle());
        if (latTextField.getText().equals("") || longTextField.getText().equals("")
                || virusTextField.getText().equals("") || contTextField.getText().equals("")
                || conditionGroup.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
            ans = false;
        }
        return ans;
    }

    public static void makeWaterSrcReportDummyData() {
        WaterPurityReport report1 = new WaterPurityReport( //top
                "2001", "Sean Buckingham", 33.68, -84.15, WaterPurityReport.Condition.safe, 200,
                200
        );
        WaterPurityReport report2 = new WaterPurityReport( //right
                "2002", "Noah Harper", 33.88, -84.75, WaterPurityReport.Condition.treatable, 210,
                210
        );
        WaterPurityReport report3 = new WaterPurityReport( //bottom
                "2003", "Chris Polack", 33.98, -84.15, WaterPurityReport.Condition.unsafe, 300,
                240
        );
        waterPurityReportList.add(report1);
        waterPurityReportList.add(report2);
        waterPurityReportList.add(report3);
        System.out.println(waterPurityReportList.size());
        System.out.println(waterPurityReportList);
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
    }

}
