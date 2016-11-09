package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controls the welcome screen displayed on application start-up
 */
public class WelcomeScreenController {

    /** reference back to mainApplication if needed */
    private MainApplication mainApplication;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    /**
     * allow for calling back to the mainApplication application
     * code if necessary
     *
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void handleLoginPressed() {
        // Open log in screen
        mainApplication.switchToLoginScreen();
    }

    @FXML
    private void handleRegisterPressed() {
        mainApplication.switchToRegisterScreen();
    }

}
