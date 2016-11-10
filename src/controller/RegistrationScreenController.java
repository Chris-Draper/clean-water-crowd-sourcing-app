package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.GenericUser;
import model.UserType;

/**
 * Controls the registration screen of the main application
 */
public class RegistrationScreenController {

    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ComboBox positionComboBox;
    private final ObservableList<UserType> userTypes =
            FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        for (UserType userType: UserType.values()) {
            userTypes.add(userType);
        }
        positionComboBox.getItems().addAll(userTypes);

    }

    @FXML
    private void handleRegisterPressed() {
        UserType type = (UserType) positionComboBox.getSelectionModel()
                .selectedItemProperty().getValue();
        if (isRegistrationInfoAcceptable(type)) {

            GenericUser loggedInUser;
            mainApplication.getDatabaseConn().registerUser(usernameTextField
                    .getText(), passwordTextField.getText(), type);
            loggedInUser = mainApplication.getDatabaseConn().verifyUser(
                    usernameTextField.getText(),
                    passwordTextField.getText()
            );
            mainApplication.setAuthenticatedUser(loggedInUser);
            mainApplication.switchToHomeScreen();
        }
    }

    private boolean isRegistrationInfoAcceptable(UserType type) {
        final int maxUserCharValue = 122;
        final int minUserCharValue = 65;
        if (("").equals(usernameTextField.getText())
            || ("").equals(passwordTextField.getText())
            || (type == null)) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
                alert.showAndWait();
                return false;
        }
        // Validate username is a valid username
         else if (!usernameTextField.getText().matches("[a-zA-Z0-9]+$")) {
            //enter another username
            Alert alert = new Alert(
                Alert.AlertType.ERROR,
                "Please re-enter username. Your username can not have"
                + "any special characters.",
                ButtonType.OK
            );
            alert.showAndWait();
            return false;
        } else if ((usernameTextField.getText().charAt(0) > maxUserCharValue)
                || (usernameTextField.getText().charAt(0) < minUserCharValue)) {
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Please re-enter username. The first letter" +
                    " of your username must be a letter",
                    ButtonType.OK);
            alert.showAndWait();
            return false;

            // Check database to see if user already exists
        } else if (mainApplication.getDatabaseConn().hasAlreadyRegistered(
                usernameTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please choose another username. The username" +
                    " entered is already in the system.", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Reloads the home screen into the application view when the back
     * button is pressed
     */
    @FXML
    public void handleBackButtonPressed() {
        mainApplication.reloadHomeScreen();
    }

    /**
     * allow for calling back to the mainApplication application
     * code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
