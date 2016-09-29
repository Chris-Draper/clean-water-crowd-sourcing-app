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

/**
 * Created by sbuck on 9/21/2016.
 */
public class LoginScreenController {

    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    private User aValidUser;

    private UserLog userLog;

    @FXML
    private void initialize() {
        aValidUser = new User("user","pass");
    }

    @FXML
    private void handleBackButtonPressed() {
        mainApplication.reloadHomeScreen();
    }

    @FXML
    private void handleLogInPressed() {
        if(usernameTextField.equals("") || passwordTextField.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
        } else if(isInputValid()) {
            mainApplication.setAuthenticatedUser(aValidUser);
            mainApplication.switchToHomeScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This Username and Password combination cannot be found", ButtonType.OK);
            alert.showAndWait();
            passwordTextField.setText("");
        }
    }

    // Check to see if values entered as username and password is acceptable
    private boolean isInputValid() {
        return usernameTextField.getText().equals(aValidUser.getUsername())
                && passwordTextField.getText().equals(aValidUser.getPassword());
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
