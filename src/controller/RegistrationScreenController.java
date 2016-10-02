package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.UserType;

import java.util.LinkedList;

/**
 * Created by ChrisPolack on 9/27/16.
 */
public class RegistrationScreenController {

    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox positionComboBox;
    private final ObservableList<UserType> userTypes = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        for (UserType userType: UserType.values()) {
            userTypes.add(userType);
        }
        positionComboBox.getItems().addAll(userTypes);

    }
    @FXML
    public void handleRegisterPressed() {
        if(usernameTextField.equals("") || passwordTextField.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
        } else {

        }
    }

    private boolean isRegistrationInfoAcceptable() {
        //validate username is a valid username  and check if username is in "database"
        if (usernameTextField.getText().contains("-'.!@#$%^&*()+=~`{}|:\"<>?[]\';/.,'")
                || usernameTextField.getText().charAt(0) > 122
                || usernameTextField.getText().charAt(0) < 65) { //enter another username
            return false;
        } else if (userLog.contains(usernameTextField.getText())) {//user is in database ALREADY

        }
        return true;
    }

    @FXML
    public void handleBackButtonPressed() {
        mainApplication.reloadHomeScreen();
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
