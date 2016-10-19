package controller;

import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.UserType;
import model.WaterSourceReport;
import java.io.IOException;
import java.util.ArrayList;

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
    private VBox userProfileVBox;
    private VBox homeScreenVBox;
    private BorderPane googleMapBorderPane;

    @FXML
    private ListView listView;

    private static int reportDisplayCounter = 0;

    private ObservableList listItems = FXCollections.observableArrayList();
    private TextArea textArea;

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton listButton;

    @FXML
    private ToggleButton profileButton;

    @FXML
    private ToggleButton waterSourceReportButton;

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
            waterSourceReportButton.setVisible(false);
            System.out.println("1");
        } else {
            waterSourceReportButton.setVisible(true);
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

    @FXML
    private void handleProfileButton(ActionEvent event) {
        if (profileButton.isSelected()) {
            waterSourceReportButton.setText("Water Report");
            if (profileButton.isSelected()) {
                rootLayout.setCenter(userProfileVBox);
            } else {
                rootLayout.setCenter(welcomeVBox);
            }
        } else {
            profileButton.setSelected(true);
        }
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
            loader_2.setLocation(MainApplication.class.getResource("../view/HomeScreen_WaterPurityReport.fxml"));
            userProfileVBox = loader_2.load();

            ListReportsController ctrl_2 = loader_2.getController();
            ctrl_2.setMainApp(mainApplication);

        } catch (IOException e) {
            System.out.println("Can't find Vboxs");
        }
    }


    public void setProfileButton(boolean selected) {
        this.profileButton.setSelected(selected);
    }

    @FXML
    private void handleHomeButtonPressed() {
        try {
            if (!homeButton.isSelected()) {
                homeButton.setSelected(true);
            } else {
                waterSourceReportButton.setText("Water Report");
                homeScreenVBox = (VBox) FXMLLoader.load(getClass().getResource("../view/InitHomeScreen.fxml"));
                rootLayout.setCenter(homeScreenVBox);
            }

        } catch (Exception e) {
            System.out.println("Can't find home: " + e);
        }
    }

    @FXML
    private void handleListButtonPressed(ActionEvent event) {
        try {
            if (listButton.isSelected()) {
                listWaterReportVBox = (VBox) FXMLLoader.load(getClass().getResource("../view/HomeScreen_ListReports.fxml"));
                ArrayList<WaterSourceReport> waterSourceReports = WaterSourceReportController.getWaterSourceReportList();

                listItems = FXCollections.observableArrayList();
                for (int i = 0; i < waterSourceReports.size(); i++) {
                    listItems.add(waterSourceReports.get(i).getReportNum()); //change later, fill with water source report objects
                    reportDisplayCounter = waterSourceReports.size();
                } //see if you can directly insert the waterSourceReports into the ListView<> parameter
                listView = new ListView<>(listItems);
                listView.setPrefHeight(490);
                listWaterReportVBox.getChildren().addAll(listView);
                rootLayout.setCenter(listWaterReportVBox);

                listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        listView.setPrefHeight(230);
                        if (textArea != null) {
                            listWaterReportVBox.getChildren().remove(textArea);
                        }
                        WaterSourceReport waterSourceReportData = null;
                        for (int i = 0; i < waterSourceReports.size(); i++) { //doing this is hella jank and NEEDS to be refactored
                            if (waterSourceReports.get(i).getReportNum().equals(listView.getSelectionModel().getSelectedItem().toString())) {
                                waterSourceReportData = waterSourceReports.get(i);
                            }
                        }
                        if (waterSourceReportData != null) {
                            textArea = new TextArea(
                                    "Report Number: " + waterSourceReportData.getReportNum() + "\n\n" +
                                            "Location Lat: " + waterSourceReportData.getLat() + "\n" +
                                            "Location Long: " + waterSourceReportData.getLong() + "\n" +
                                            "Date of report: " + waterSourceReportData.getDate() + "\n" +
                                            "Time of report: " + waterSourceReportData.getTime() + "\n" +
                                            "Reported By: " + waterSourceReportData.getReporterName() + "\n" +
                                            "Source Type: " + waterSourceReportData.getSourceType() + "\n" +
                                            "Water Condition: " + waterSourceReportData.getCondition()
                            );
                            textArea.setWrapText(true);
                            textArea.setPrefHeight(260);
                            listWaterReportVBox.getChildren().addAll(textArea);
                        }
                    }
                });
            } else {
                listButton.setSelected(true);
            }
        } catch (IOException e) {
            System.out.println("Failed to find list button!");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleWaterSourceReportButton(ActionEvent event) {
        try {
            if (waterSourceReportButton.getText().equals("Cancel Report")) {
                rootLayout.setCenter(homeScreenVBox);
                homeButton.setSelected(true);
                waterSourceReportButton.setText("Water Report");
            } else {
                listWaterReportVBox = (VBox) FXMLLoader.load(getClass().getResource("../view/HomeScreen_WaterSourceReport.fxml"));
                rootLayout.setCenter(listWaterReportVBox);
                waterSourceReportButton.setText("Cancel Report");
            }
        } catch (IOException e) {
            System.out.println("Failed to find vbox2!");
            e.printStackTrace();
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