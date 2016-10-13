package controller;

import com.sun.tools.javac.jvm.Gen;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UserLog;
import model.WaterSourceReport;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import model.WaterSourceReport;

//reference http://stackoverflow.com/questions/19342259/how-to-create-multiple-javafx-controllers-with-different-fxml-files to fix issues

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

    @FXML
    private VBox listWaterReportVBox;

    @FXML
    private VBox userProfileVBox;

    @FXML
    private Button profileButton;


    @FXML
    private ListView listView;

    private static int reportDisplayCounter = 0;

    private ObservableList waterSourceReportsOList = FXCollections.observableArrayList(WaterSourceReport.getReportList());
    private ObservableList listItems = FXCollections.observableArrayList();
    private TextArea textArea;

    @FXML
    private ToggleButton listButton;

    @FXML
    private ToggleButton profileButton;

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
        loadVBoxs();
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/HomeScreenUser.fxml"));
            userProfileVBox = loader.load();

            // Give the controller access to the main app.
            UserProfileController ctrl = loader.getController();
            ctrl.setMainApp(mainApplication);
        } catch (IOException e) {
            System.out.println("Can't find Vboxs");
        }
    }


    public void setProfileButton(boolean selected) {
        this.profileButton.setSelected(selected);
    }

    @FXML
    private void handleListButtonPressed(ActionEvent event) {
        try {
            if (listButton.isSelected()) {
                profileButton.setText("Edit Profile");
                vbox2 = (VBox) FXMLLoader.load(getClass().getResource("../view/HomeScreen_ListView.fxml"));
                ArrayList<WaterSourceReport> waterSourceReports = WaterSourceReport.getReportList();
                if (rootLayout.getCenter() == vbox1) {
                    for (int i = reportDisplayCounter; i < waterSourceReports.size(); i++) {
                        listItems.add(waterSourceReports.get(i).getReportNum()); //change later, fill with water source report objects
                        reportDisplayCounter = waterSourceReports.size();
                    } //see if you can directly insert the waterSourceReports into the ListView<> parameter
                    listView = new ListView<>(listItems);
                    listView.setPrefHeight(490);
                    vbox2.getChildren().addAll(listView);
                    rootLayout.setCenter(vbox2);
                    listButton.setText("Back");
                    listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            listView.setPrefHeight(230);
                            if (textArea != null) {
                                vbox2.getChildren().remove(textArea);
                            }
                            WaterSourceReport waterSourceReportData = null;
                            for (int i = 0; i < waterSourceReports.size(); i++) { //doing this is hella jank and NEEDS to be refactored
                                if (waterSourceReports.get(i).getReportNum().equals( listView.getSelectionModel().getSelectedItem().toString())) {
                                    waterSourceReportData = waterSourceReports.get(i);
                                }
                            }
                            textArea = new TextArea(
                                "Report Number: " + waterSourceReportData.getReportNum() + "\n\n" +
                                "Location: " + waterSourceReportData.getLocation() + "\n" +
                                "Date of report: " + waterSourceReportData.getDate() + "\n" +
                                "Time of report: " + waterSourceReportData.getTime() + "\n" +
                                "Reported By: " + waterSourceReportData.getReporterName() + "\n" +
                                "Source Type: " + waterSourceReportData.getSourceType() + "\n" +
                                "Water Condition: " + waterSourceReportData.getCondition()
                            );
                            textArea.setWrapText(true);
                            textArea.setPrefHeight(260);
                            vbox2.getChildren().addAll(textArea);

                        }
                    });

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

}
