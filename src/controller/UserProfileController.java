package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.GenericUser;
import model.UserType;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nharper32 on 10/6/16.
 */
public class UserProfileController {

    private MainApplication mainApplication;

    private VBox rootLayout;

    @FXML
    private Button updateProfileButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox titleComboBox;
    private final ObservableList<UserType> userTypes = FXCollections.observableArrayList();

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
    private VBox vbox2;

    private GenericUser currentUser;

    @FXML
    private void initialize() {
        for (UserType userType: UserType.values()) {
            userTypes.add(userType);
        }
        titleComboBox.getItems().addAll(userTypes);
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        try {
            fillProfile();
        } catch (SQLException e) {
            System.out.println("Error closing statement after updating profile: " + e);
        }
    }

    private void fillProfile() throws SQLException {

        int userID = mainApplication.getAuthenticatedUser().getID();
        String[] infoFields = mainApplication.getDatabaseConn().getProfileInfo(userID);

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

        titleComboBox.setValue(mainApplication.getAuthenticatedUser().getUserType());
    }

    @FXML
    public void handleUpdateProfileButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile information saved.", ButtonType.OK);
        alert.showAndWait();
        mainApplication.updateUserInfo(nameTextField, emailTextField, addressNumField,
                streetNameField, zipField, cityField, stateField, phoneNumField);
    }
}
