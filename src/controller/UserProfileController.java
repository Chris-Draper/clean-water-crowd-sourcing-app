package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.GenericUser;
import model.UserType;

import java.io.IOException;

/**
 * Created by nharper32 on 10/6/16.
 */
public class UserProfileController {

    private MainApplication mainApplication;

    private VBox rootLayout;

    @FXML
    private Button updateProfileButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox titleComboBox;
    private final ObservableList<UserType> userTypes = FXCollections.observableArrayList();

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField addressNumField;

    @FXML
    private TextField zipField;

    @FXML
    private TextField streetNameField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField phoneNumField;

    @FXML
    private VBox vbox2;



    private GenericUser currentUser;

    @FXML
    private void initialize() {
        for (UserType userType: UserType.values()) {
            userTypes.add(userType);
        }
        titleComboBox.getItems().addAll(userTypes);
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        fillProfile();
    }

    private void fillProfile() {
        currentUser = mainApplication.getAuthenticatedUser();
        if (currentUser.getFullName() != null) {
            nameTextField.setText(currentUser.getFullName());
        }
        if (currentUser.getEmailAddress() != null) {
            emailTextField.setText(currentUser.getEmailAddress());
        }
        if (currentUser.getHomeAddressNum() != null) {
            addressNumField.setText(currentUser.getHomeAddressNum());
        }
        if (currentUser.getHomeAddressStreet() != null) {
            streetNameField.setText(currentUser.getHomeAddressStreet());
        }
        if (currentUser.getHomeAddressCity() != null) {
            cityField.setText(currentUser.getHomeAddressCity());
        }
        if (currentUser.getHomeAddressState() != null) {
            stateField.setText(currentUser.getHomeAddressState());
        }
        if (currentUser.getHomeAddressZip() != null) {
            zipField.setText(currentUser.getHomeAddressZip());
        }
        if (currentUser.getPhoneNumber() != null) {
            phoneNumField.setText(currentUser.getPhoneNumber());
        }
        if (currentUser.getUserType() != null) {
            titleComboBox.setValue(currentUser.getUserType());
        }
    }

    
    @FXML
    public void handleUpdateProfileButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile information saved.", ButtonType.OK);
        alert.showAndWait();
        mainApplication.updateUserInfo(nameTextField, emailTextField, addressNumField,
                streetNameField, zipField, cityField, stateField, phoneNumField);
    }




}
