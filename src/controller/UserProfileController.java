package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    }

    public void setMainApp(MainApplication mainApplication) {
        System.out.println("First");
        this.mainApplication = mainApplication;
    }

    private void fillProfile() {
        if (currentUser.getFullName() != null) {
         //   nameTextField.setText(currentUser.getFullName());
        }
        if (currentUser.getEmailAddress() != null) {
          //  emailTextField.setText(currentUser.getEmailAddress());
        }
        if (currentUser.getHomeAddress() != null) {
          //  addressNumField.setText(currentUser.getHomeAddress());
        }
        if (currentUser.getPhoneNumber() != null) {
         //   phoneNumField.setText(currentUser.getPhoneNumber());
        }
    }

    @FXML
    public void handleUpdateProfileButton() {
        System.out.println("Second");
        mainApplication.updateUserInfo(nameTextField, emailTextField, addressNumField,
                streetNameField, zipField, cityField, stateField);
    }

    public TextField getNameTextField() {return nameTextField;}




}
