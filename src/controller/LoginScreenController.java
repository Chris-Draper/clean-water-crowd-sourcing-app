package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.GenericUser;

/**
 * Controls the initial login screen showed on application start-up
 */
public class LoginScreenController {

    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextField; //used on line 31

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleBackButtonPressed() {
        mainApplication.reloadHomeScreen();
    }

    @FXML
    private void handleLogInPressed() {
        if(("").equals(usernameTextField.getText())
            || ("").equals(passwordField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
        } else {

            GenericUser loggedInUser = isInputValid();

            if(loggedInUser != null) {
                mainApplication.setAuthenticatedUser(loggedInUser);
                //System.out.println(mainApplication.getAuthenticatedUser());
                mainApplication.switchToHomeScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "This Username and Password combination cannot"
                        + " be found", ButtonType.OK);
                alert.showAndWait();
                passwordField.setText("");
            }
        }
    }

    // Check to see if values entered as username and password is acceptable
    private GenericUser isInputValid() {
        return mainApplication.getDatabaseConn().verifyUser(usernameTextField
                .getText(), passwordField.getText());
    }

    /**
     * allow for calling back to the mainApplication application
     * code if necessary
     *
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

}
