package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.UserType;

/**
 * Created by sbuck on 9/21/2016.
 */
public class LoginScreenController {

    private MainApplication mainApplication;

    @FXML
    private ComboBox positionComboBox;

    private final ObservableList<UserType> usertypes = FXCollections.observableArrayList();

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        for (UserType usertype : UserType.values()) {
            usertypes.add(usertype);
        }
        positionComboBox.getItems().addAll(usertypes);
    }

    @FXML
    private void handleBackButtonPressed() {
        mainApplication.reloadHomeScreen();
    }

    @FXML
    private void handleLogInPressed() {
        mainApplication.switchToHomeScreen();
        //check to see if inserted credentials are valid
       // if (isInputValid()) {


    }

    // Check to see if values entered as username and password is acceptable
    private boolean isInputValid() {

        return true; //EDIT LATER
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

}
