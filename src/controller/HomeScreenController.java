package controller;

import fxapp.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.UserType;

import java.awt.*;
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

    private VBox listPurityReportVbox;
    private VBox listWaterReportVBox;
    private VBox purityReportVBox;
    private VBox userProfileVBox;
    private VBox welcomeMsgVBox;
    private VBox waterReportVbox;
    private VBox historyGraphVbox;
    private BorderPane googleMapBorderPane;

    @FXML
    private ToggleGroup sideNavigation;
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
    private ToggleButton viewHistoryGraphButton;

    @FXML
    private Label welcomeMsg;

    private UserProfileController ctrl;
    private ListWaterReportsController ctrl_2;
    private WaterPurityReportController ctrl_3;
    private WaterSourceReportController ctrl_4;
    private ListPurityReportsController ctrl_5;
    private HistoryGraphController ctrl_7;

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
            listPurityButton.setVisible(false);
        }
        if (!mainApplication.getAuthenticatedUser().getUserType().equals(UserType.Manager)) {
            viewHistoryGraphButton.setVisible(false);
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
            ctrl = loader_1.getController();
            ctrl.setMainApp(mainApplication);

            FXMLLoader loader_2 = new FXMLLoader();
            loader_2.setLocation(MainApplication.class.getResource("../view/HomeScreen_ListWaterReports.fxml"));
            listWaterReportVBox = loader_2.load();

            ctrl_2 = loader_2.getController();
            ctrl_2.setMainApp(mainApplication);

            FXMLLoader loader_3 = new FXMLLoader();
            loader_3.setLocation(MainApplication.class.getResource("../view/HomeScreen_WaterPurityReport.fxml"));
            purityReportVBox = loader_3.load();

            ctrl_3 = loader_3.getController();
            ctrl_3.setMainApp(mainApplication);

            FXMLLoader loader_4 = new FXMLLoader();
            loader_4.setLocation(MainApplication.class.getResource("../view/HomeScreen_WaterSourceReport.fxml"));
            waterReportVbox = loader_4.load();

            ctrl_4 = loader_4.getController();
            ctrl_4.setMainApplication(mainApplication);

            FXMLLoader loader_5 = new FXMLLoader();
            loader_5.setLocation(MainApplication.class.getResource("../view/HomeScreen_ListPurityReports.fxml"));
            listPurityReportVbox = loader_5.load();

            ctrl_5 = loader_5.getController();
            ctrl_5.setMainApplication(mainApplication);

            FXMLLoader loader_6 = new FXMLLoader();
            loader_6.setLocation((MainApplication.class.getResource("../view/InitHomeScreen.fxml")));
            welcomeMsgVBox = loader_6.load();

            FXMLLoader loader_7 = new FXMLLoader();
            loader_7.setLocation(MainApplication.class.getResource("../view/HomeScreen_HistoryGraph.fxml"));
            historyGraphVbox = loader_7.load();

            ctrl_7 = loader_7.getController();
            ctrl_7.setMainApplication(mainApplication);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't find Vboxs");
            System.out.println(e);
        }
    }

    @FXML
    private void handleHomeButtonPressed() {
        if (homeButton.isSelected()) {
            rootLayout.setCenter(welcomeMsgVBox);
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
                rootLayout.setCenter(welcomeMsgVBox);
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
                ctrl_2.clearList();
                ctrl_2.populateList();
            } else {
                rootLayout.setCenter(welcomeMsgVBox);
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
                ctrl_5.clearList();
                ctrl_5.populateList();
            } else {
                rootLayout.setCenter(welcomeMsgVBox);
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
                rootLayout.setCenter(welcomeMsgVBox);
            }
        } else {
            waterSourceReportButton.setSelected(true);
        }
    }

    @FXML
    private void handleListPurityButtonPressed(ActionEvent event) {
        if (listPurityButton.isSelected()) {
            if (listPurityButton.isSelected()) {
                rootLayout.setCenter(listPurityReportVbox);
                ctrl_5.clearList();
                ctrl_5.populateList();
            } else {
                rootLayout.setCenter(welcomeMsgVBox);
            }
        } else {
            listPurityButton.setSelected(true);
        }
    }

    @FXML
    private void handleViewHistoryGraph(ActionEvent event) {
        if (viewHistoryGraphButton.isSelected()) {
            if (viewHistoryGraphButton.isSelected()) {
                rootLayout.setCenter(historyGraphVbox);
                ctrl_7.fillLocationList();
            } else {
                rootLayout.setCenter(welcomeMsgVBox);
            }
        } else {
            viewHistoryGraphButton.setSelected(true);
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