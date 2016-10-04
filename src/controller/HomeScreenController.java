package controller;

import fxapp.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UserLog;

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
    private Button profileBackButton;




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

}
