package controller;

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

//reference http://stackoverflow.com/questions/19342259/how-to-create-multiple-javafx-controllers-with-different-fxml-files to fix issues

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

    @FXML
    public Button waterSourceReportButton;

    @FXML
    private Button listButton;

    @FXML
    private ListView listView;

    private ObservableList listItems = FXCollections.observableArrayList();
    private TextArea textArea;

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
                listButton.setText("List Reports");
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

    @FXML
    private void handleListButtonPressed(ActionEvent event) {
        try {
            if (event.getSource() == listButton) {
                profileButton.setText("Edit Profile");
                vbox2 = (VBox) FXMLLoader.load(getClass().getResource("../view/HomeScreen_ListView.fxml"));
                if (rootLayout.getCenter() == vbox1) {
                    for (int i = 0; i < 30; i++) listItems.add("Fake Water Source Report #" + i); //change later, fill with water source report objects
                    listView = new ListView<>(listItems);
                    listView.setPrefHeight(490);
                    vbox2.getChildren().addAll(listView);
                    rootLayout.setCenter(vbox2);
                    listButton.setText("Back");
                    listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            listView.setPrefHeight(230);
                            if (textArea != null) vbox2.getChildren().remove(textArea);
                            textArea = new TextArea(
                                    "Title: " + listView.getSelectionModel().getSelectedItem().toString() +
                                    "\n\nLocation: Atlanta, Georgia\n" +
                                            "Time of report: 9:43 pm EST\n" +
                                            "Reported By: Sean Buckingham\n\n" +
                                            "General Information:\n" +
                                            "The water was extra cloudy. Definitely not drinkable but hey its good" +
                                            " I reported this water in Clean Water Crowdsourcing right, I mean if i hadn't " +
                                            "said anything no one would even know there was water here. THANKS CLEAN WATER " +
                                            "CROWDSOURCING. YOU ROCK!!!"
                            );
                            textArea.setWrapText(true);
                            textArea.setPrefHeight(260);
                            vbox2.getChildren().addAll(textArea);

                        }
                    });

                } else {
                    rootLayout.setCenter(vbox1);
                    listButton.setText("List Reports");
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to find list button!");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleWaterSourceReportButton(ActionEvent event) {
        try {
            if (event.getSource() == waterSourceReportButton) {
                vbox2 = (VBox) FXMLLoader.load(getClass().getResource("../view/SubmitReportView.fxml"));
                if (rootLayout.getCenter() == vbox1) {
                    rootLayout.setCenter(vbox2);
                    waterSourceReportButton.setText("Back");
                } else {
                    rootLayout.setCenter(vbox1);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to find vbox2!");
            e.printStackTrace();
        }
    }

}
