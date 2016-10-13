package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.GenericUser;
import model.User;
import model.UserLog;
import model.UserType;
import model.UserLog;


/**
 * Created by sbuck on 9/21/2016.
 */
public class LoginScreenController {

    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    private GenericUser aValidUser;

    private UserLog userLog;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleBackButtonPressed() {
        mainApplication.reloadHomeScreen();
    }

    @FXML
    private void handleLogInPressed() {
        if(usernameTextField.getText().equals("") || passwordField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
        } else if(isInputValid()) {
            aValidUser = userLog.getCurrentUser(usernameTextField.getText());
            mainApplication.setAuthenticatedUser(aValidUser);
            mainApplication.switchToHomeScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This Username and Password combination cannot be found", ButtonType.OK);
            alert.showAndWait();
            passwordField.setText("");
        }
    }

    // Check to see if values entered as username and password is acceptable
    private boolean isInputValid() {
        GenericUser potentialUser = new User(usernameTextField.getText(),passwordField.getText());
        return userLog.contains(potentialUser);
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        userLog = mainApplication.getUserlog();
    }

}
