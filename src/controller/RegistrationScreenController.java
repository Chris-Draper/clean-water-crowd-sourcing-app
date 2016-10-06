package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import sun.security.util.Password;

import java.util.LinkedList;

/**
 * Created by ChrisPolack on 9/27/16.
 */
public class RegistrationScreenController {

    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField passwordConfirmField;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox positionComboBox;
    private final ObservableList<UserType> userTypes = FXCollections.observableArrayList();

    private UserLog userLog;


    @FXML
    private void initialize() {
        for (UserType userType: UserType.values()) {
            userTypes.add(userType);
        }
        positionComboBox.getItems().addAll(userTypes);

    }

    @FXML
    public void handleRegisterPressed() {
        UserType type = (UserType) positionComboBox.getSelectionModel().selectedItemProperty().getValue();
        GenericUser newUser = null;
        if (isRegistrationInfoAcceptable(type)) {
            if (type.equals(UserType.User)) {
                newUser = new User(usernameTextField.getText(),
                        passwordTextField.getText());
            } else if (type.equals(UserType.Worker)) {
                newUser = new Worker(usernameTextField.getText(),
                        passwordTextField.getText());
            } else if (type.equals(UserType.Manager)) {
                newUser = new Manager(usernameTextField.getText(),
                        passwordTextField.getText());
            } else if (type.equals(UserType.Administrator)) {
                newUser = new Administrator(usernameTextField.getText(),
                        passwordTextField.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Error creating user.", ButtonType.OK);
                alert.showAndWait();
            }
            userLog.addUser(newUser);
            mainApplication.setAuthenticatedUser(newUser);
            mainApplication.switchToHomeScreen();
        } else {
            passwordTextField.setText("");
            passwordConfirmField.setText("");
        }
    }

    private boolean isRegistrationInfoAcceptable(UserType type) {
        if (usernameTextField.getText().equals("") || passwordTextField.getText().equals("") || type == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please complete all fields", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        // Validate username is a valid username and check if username is in "database"
         else if (!usernameTextField.getText().matches("[a-zA-Z0-9]+$")) { //enter another username
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please re-enter username. Your username" +
                            " can not have any special characters.", ButtonType.OK);
            alert.showAndWait();
            return false;
        } else if (usernameTextField.getText().charAt(0) > 122
                || usernameTextField.getText().charAt(0) < 65) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please re-enter username. The first letter" +
                            " of your username must be a letter", ButtonType.OK);
            alert.showAndWait();
            return false;
        } else if (userLog.hasAlreadyRegistered(usernameTextField.getText())) {//user is in database ALREADY
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please choose another username. The username" +
                            " entered is already in the system.", ButtonType.OK);
            alert.showAndWait();
            return false;
        } else if (!passwordTextField.getText().equals(passwordConfirmField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Passwords do not match.", ButtonType.OK);
            alert.showAndWait();
            return false;
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
        userLog = mainApplication.getUserlog();
    }
}
