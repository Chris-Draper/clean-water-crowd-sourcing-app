package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.UserType;

import java.util.Collections;

/**
 * Controls the user profile view of the main application
 */
public class UserProfileController {

    private MainApplication mainApplication;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<UserType> titleComboBox;
    private final ObservableList<UserType> userTypes =
            FXCollections.observableArrayList();

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField addressNumField;

    @FXML
    private TextField zipField;

    @FXML
    private TextField streetNameField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField phoneNumField;

    @FXML
    private void initialize() {
        Collections.addAll(userTypes, UserType.values());
        titleComboBox.getItems().addAll(userTypes);
    }

    /**
     * Updates the data within the main application
     *
     * @param mainApplication - a reference to the mainApp
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        try {
            int userID = mainApplication.getAuthenticatedUser().getID();
            String[] infoFields = mainApplication.getDatabaseConn()
                    .getProfileInfo(userID);

            if (infoFields[0] != null) {
                nameTextField.setText(infoFields[0]);
            }
            if (infoFields[1] != null) {
                emailTextField.setText(infoFields[1]);
            }
            if (infoFields[2] != null) {
                addressNumField.setText(infoFields[2]);
            }
            if (infoFields[3] != null) {
                streetNameField.setText(infoFields[3]);
            }
            if (infoFields[4] != null) {
                zipField.setText(infoFields[4]);
            }
            if (infoFields[5] != null) {
                cityField.setText(infoFields[5]);
            }
            if (infoFields[6] != null) {
                stateField.setText(infoFields[6]);
            }
            if (infoFields[7] != null) {
                phoneNumField.setText(infoFields[7]);
            }

            titleComboBox.setValue(mainApplication.getAuthenticatedUser()
                    .getUserType());
        } catch (Exception e) {
            //System.out.println("Error closing statement after updating" +
            //        " profile: " + e);
        }
    }

    /**
     * Updates the user profiles on
     */
    @FXML
    public void handleUpdateProfileButton() {
        mainApplication.updateUserInfo(nameTextField, emailTextField,
                addressNumField, streetNameField, zipField, cityField,
                stateField, phoneNumField);

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION,
                "Profile information saved.",
                ButtonType.OK);
        alert.showAndWait();
    }
}
