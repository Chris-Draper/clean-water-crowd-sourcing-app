package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.GenericUser;
import model.WaterPurityReport;
import model.WaterSourceReport;

import javax.swing.*;
import java.awt.*;
import java.awt.Label;
import java.awt.TextField;
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
    private AbstractButton safeButton;

    @FXML
    private AbstractButton treatableButton;

    @FXML
    private AbstractButton unsafeButton;

    private ButtonGroup conditionGroup;

    private static Integer reportNum = 2001;

    private GenericUser currentUser = mainApplication.getAuthenticatedUser();

    private static ArrayList<WaterPurityReport> waterPurityReportList;

    private Date date;

    @FXML
    private void initialize() {
        //set text for all labels based on person doing this
        reportNumLabel.setText(reportNum.toString());
        reporterNameLabel.setText(currentUser.getUsername());
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateLabel.setText(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("HH:mm");
        timeLabel.setText(dateFormat.format(date));

        conditionGroup.add(safeButton);
        conditionGroup.add(treatableButton);
        conditionGroup.add(unsafeButton);

        //set visbility to false for now until we add functionality to input
        //street address and come up with a pin instead of long lat nums
        addressGridPane.setVisible(false);
    }

    @FXML
    private void handleSubmitButton() {
        if(isCompleted()) {
            WaterPurityReport.Condition cond;
            if (conditionGroup.getSelection() == safeButton) {
                cond = WaterPurityReport.Condition.safe;
            } else if (conditionGroup.getSelection() == treatableButton){
                cond = WaterPurityReport.Condition.treatable;
            } else {
                cond = WaterPurityReport.Condition.unsafe;
            }
            double lat = Double.parseDouble(latTextField.getText());
            double longit = Double.parseDouble(longTextField.getText());
            double virus = Double.parseDouble(virusTextField.getText());
            double cont = Double.parseDouble(contTextField.getText());

            waterPurityReportList.add(new WaterPurityReport(reportNum, currentUser.getUsername(),
                    lat, longit, cond, virus, cont));
            reportNum++;
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
                || conditionGroup.getSelection() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
            ans = false;
        }
        return ans;
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     *
     * @param mainApplication the reference to the FX Application instance
     */

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

}
