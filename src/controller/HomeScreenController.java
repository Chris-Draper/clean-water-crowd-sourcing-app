package controller;

import fxapp.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.UserType;

import java.io.IOException;

/**
 * Created by nharper32 on 9/24/16.
 */
public class HomeScreenController {

    private MainApplication mainApplication;

    private BorderPane rootLayout;

    private VBox rootVbox;

    @FXML
    private MenuBar topNavigation;

    @FXML
    private Menu fileMenu;

    @FXML
    private Menu editMenu;

    @FXML
    private Menu helpMenu;

    @FXML
    private javafx.scene.control.MenuItem fileLogout;

    @FXML
    private javafx.scene.control.MenuItem fileClose;

    @FXML
    private VBox welcomeVBox;
    private VBox listWaterReportVBox;
    private VBox purityReportVBox;
    private VBox userProfileVBox;
    private VBox homeScreenVBox;
    private VBox waterReportVbox;
    private VBox listPurityReportsVbox;
    private BorderPane googleMapBorderPane;

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton listButton;

    @FXML
    private ToggleButton profileButton;

    @FXML
    private ToggleButton waterSourceReportButton;

    @FXML
    private ToggleButton purityReportButton;

    @FXML
    private ToggleButton listPurityButton;

    @FXML
    private ToggleButton googleMapsButton;

    @FXML
    private void initialize() {
    }

    /**
     * allow for calling back to the mainApplication application code if necessary
     *
     * @param mainApplication the reference to the FX Application instance
     */

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        rootLayout = mainApplication.getRootLayout();
        homeButton.setSelected(true);
        loadVBoxs();
        if (mainApplication.getAuthenticatedUser().getUserType().equals(UserType.User)
                || mainApplication.getAuthenticatedUser().getUserType().equals(UserType.Administrator)) {
            purityReportButton.setVisible(false);
            System.out.println("1");
        } else {
            purityReportButton.setVisible(true);
            System.out.println("2");
        }
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

    private void loadVBoxs() {
        try {
            FXMLLoader loader_1 = new FXMLLoader();
            loader_1.setLocation(MainApplication.class.getResource("../view/HomeScreen_UserProfile.fxml"));
            userProfileVBox = loader_1.load();

            // Give the controller access to the main app.
            UserProfileController ctrl = loader_1.getController();
            ctrl.setMainApp(mainApplication);

            FXMLLoader loader_2 = new FXMLLoader();
            loader_2.setLocation(MainApplication.class.getResource("../view/HomeScreen_ListWaterReports.fxml"));
            listWaterReportVBox = loader_2.load();

            ListWaterReportsController ctrl_2 = loader_2.getController();
            ctrl_2.setMainApp(mainApplication);


            FXMLLoader loader_3 = new FXMLLoader();
            loader_3.setLocation(MainApplication.class.getResource("../view/HomeScreen_WaterPurityReport.fxml"));
            purityReportVBox = loader_3.load();

            WaterPurityReportController ctrl_3 = loader_3.getController();
            ctrl_3.setMainApp(mainApplication);

            FXMLLoader loader_4 = new FXMLLoader();
            loader_4.setLocation((MainApplication.class.getResource("../view/HomeScreen_WaterSourceReport.fxml")));
            waterReportVbox = loader_4.load();

            WaterSourceReportController ctrl_4 = loader_4.getController();
            ctrl_4.setMainApplication(mainApplication);

            FXMLLoader loader_5 = new FXMLLoader();
            loader_5.setLocation((MainApplication.class.getResource("../view/InitHomeScreen.fxml")));
            homeScreenVBox = loader_5.load();

        } catch (IOException e) {
            System.out.println("Can't find Vboxs");
        }
    }

    @FXML
    private void handleHomeButtonPressed() {
        if (homeButton.isSelected()) {
            if (homeButton.isSelected()) {
                rootLayout.setCenter(homeScreenVBox);
            } else {
                rootLayout.setCenter(welcomeVBox);
            }
        } else {
            homeButton.setSelected(true);
        }
    }


    @FXML
    private void handleProfileButton(ActionEvent event) {
        if (profileButton.isSelected()) {
            if (profileButton.isSelected()) {
                rootLayout.setCenter(userProfileVBox);
            } else {
                rootLayout.setCenter(welcomeVBox);
            }
        } else {
            profileButton.setSelected(true);
        }
    }

    @FXML
    private void handleListButtonPressed(ActionEvent event) {
        if (listButton.isSelected()) {
            if (listButton.isSelected()) {
                rootLayout.setCenter(listWaterReportVBox);
            } else {
                rootLayout.setCenter(welcomeVBox);
            }
        } else {
            listButton.setSelected(true);
        }
    }

    @FXML
    private void handlePurityReportButton(ActionEvent event) {
        if (purityReportButton.isSelected()) {
            if (purityReportButton.isSelected()) {
                rootLayout.setCenter(purityReportVBox);
            } else {
                rootLayout.setCenter(welcomeVBox);
            }
        } else {
            purityReportButton.setSelected(true);
        }
    }

    @FXML
    private void handleWaterSourceReportButton(ActionEvent event) {
        if(waterSourceReportButton.isSelected()) {
            if(waterSourceReportButton.isSelected()) {
                rootLayout.setCenter(waterReportVbox);
            } else {
                rootLayout.setCenter(welcomeVBox);
            }
        } else {
            waterSourceReportButton.setSelected(true);
        }
    }

    @FXML
    private void handleListPurityButtonPressed(ActionEvent event) {
        if(listPurityButton.isSelected()) {
            if(listPurityButton.isSelected()) {
                rootLayout.setCenter(listPurityReportsVbox);
            } else {
                rootLayout.setCenter(welcomeVBox);
            }
        } else {
            listPurityButton.setSelected(true);
        }
    }


    @FXML
    private void handleGoogleMapsButton(ActionEvent event) {
        try {
            googleMapBorderPane = FXMLLoader.load(getClass().getResource("../view/HomeScreen_GoogleMaps.fxml"));
            rootLayout.setCenter(googleMapBorderPane);
            googleMapsButton.setSelected(true);
        } catch (IOException e) {
            System.out.println("Failed to open Google Maps View!");
            e.printStackTrace();
        }
    }

}