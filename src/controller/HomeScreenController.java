package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GenericUser;
import model.UserLog;
import model.UserType;
import sun.applet.Main;

import java.io.IOException;

/**
 * Created by nharper32 on 9/24/16.
 */
public class HomeScreenController {

    private MainApplication mainApplication;

    private BorderPane rootLayout;

    @FXML
    private MenuBar topNavigation;

    @FXML
    private Menu fileMenu;

    @FXML
    private Menu editMenu;

    @FXML
    private Menu helpMenu;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    @FXML
    private Button profileButton;

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
    private void initialize() {
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        rootLayout = mainApplication.getRootLayout();
        vbox1 = (VBox) rootLayout.getCenter();
        
    }

    @FXML
    private void handleCloseMenuPressed() {
        System.exit(0);
    }

    @FXML
    private void handleLogoutMenuPressed() {
        mainApplication.logoutUser();
        mainApplication.reloadHomeScreen();
    }

    @FXML
    private void handleProfileButton(ActionEvent event) {
        try {
            if (event.getSource() == profileButton) {
                vbox2 = (VBox) FXMLLoader.load(getClass().getResource("../view/HomeScreenUser.fxml"));
                String oldText = profileButton.getText();
                if (rootLayout.getCenter() == vbox1) {
                    rootLayout.setCenter(vbox2);
                    profileButton.setText("Back");
                    fillProfile();
                } else {
                    rootLayout.setCenter(vbox1);
                    profileButton.setText("Edit Profile");

                }
            }
        } catch (IOException e) {
            System.out.println("Failed to find vbox2!");
            e.printStackTrace();
        }

    }

    private void fillProfile() {
        GenericUser currentUser = mainApplication.getAuthenticatedUser();
        if (currentUser.getFullName() != null) {
            nameTextField.setText(currentUser.getFullName());
        }
        if (currentUser.getEmailAddress() != null) {
            emailTextField.setText(currentUser.getEmailAddress());
        }
        if (currentUser.getHomeAddress() != null) {
            addressNumField.setText(currentUser.getHomeAddress());
        }
        if (currentUser.getPhoneNumber() != null) {
            phoneNumField.setText(currentUser.getPhoneNumber());
        }
    }

    @FXML
    public void handleUpdateProfileButton() {
        GenericUser currentUser = mainApplication.getAuthenticatedUser();
        currentUser.setFullName(nameTextField.getText());
        currentUser.setEmailAddress(emailTextField.getText());
        currentUser.setHomeAddress(addressNumField.getText() + streetNameField.getText()
                + zipField.getText() + cityField.getText() + stateField.getText());
        currentUser.setPhoneNumber(phoneNumField.getText());
    }

}
