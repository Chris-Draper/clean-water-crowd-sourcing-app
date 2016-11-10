package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import java.sql.SQLException;

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
    private void handleRegisterPressed() {
        UserType type = (UserType) positionComboBox.getSelectionModel().selectedItemProperty().getValue();
        if (isRegistrationInfoAcceptable(usernameTextField.getText(), passwordTextField.getText(),
                type, mainApplication.getDatabaseConn())) {

            GenericUser loggedInUser;
            mainApplication.getDatabaseConn().registerUser(usernameTextField.getText(), passwordTextField.getText(), type);
            loggedInUser = mainApplication.getDatabaseConn().verifyUser(usernameTextField.getText(), passwordTextField.getText());
            mainApplication.setAuthenticatedUser(loggedInUser);
            mainApplication.switchToHomeScreen();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Your input is not valid. Please re-enter username and password.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public boolean isRegistrationInfoAcceptable(String username, String password, UserType type,
                                                DatabaseInterface db) {
        if (username.equals("") || password.equals("") || type == null) {
            return false;
        } else if (!username.matches("[a-zA-Z0-9]+$")) {
            return false;
        } else if (!password.matches("[a-zA-Z0-9]+$")) {
            return false;
        } else if (db.hasAlreadyRegistered(username)) {
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
    }
}
