package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
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

    private User newUser;

    @FXML
    private void initialize() {
        for (UserType usertype : UserType.values()) {
            usertypes.add(usertype);
        }
        positionComboBox.getItems().addAll(usertypes);
        newUser = new User("user","pass");
    }

    @FXML
    private void handleBackButtonPressed() {
        mainApplication.reloadHomeScreen();
    }

    @FXML
    private void handleLogInPressed() {
        if(isInputValid()) {
            mainApplication.switchToHomeScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This Username and Password combination cannot be found", ButtonType.OK);
            alert.showAndWait();
            passwordTextField.setText("");
        }


        //check to see if inserted credentials are valid
       // if (isInputValid()) {


    }

    // Check to see if values entered as username and password is acceptable
    private boolean isInputValid() {
        if(usernameTextField.getText().equals(newUser.getUsername())
                && passwordTextField.getText().equals(newUser.getPassword())) {
            return true;
        }
        return false; //EDIT LATER
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

}
