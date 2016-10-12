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

import javax.swing.*;
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

    @FXML
    private VBox welcomeVBox;

    @FXML
    private VBox listWaterReportVBox;

    @FXML
    private VBox userProfileVBox;

    @FXML
    private ListView listView;

    private ObservableList listItems = FXCollections.observableArrayList();
    private TextArea textArea;

    @FXML
    private ToggleButton listButton;

    @FXML
    private ToggleButton profileButton;

    @FXML
    private void initialize() {
    }

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
                listWaterReportVBox = (VBox) FXMLLoader.load(getClass().getResource("../view/HomeScreen_ListView.fxml"));

                for (int i = 0; i < 30; i++)
                    listItems.add("Fake Water Source Report #" + i); //change later, fill with water source report objects
                listView = new ListView<>(listItems);
                listView.setPrefHeight(490);
                listWaterReportVBox.getChildren().addAll(listView);
                rootLayout.setCenter(listWaterReportVBox);
                listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        listView.setPrefHeight(230);
                        if (textArea != null) listWaterReportVBox.getChildren().remove(textArea);
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
                        listWaterReportVBox.getChildren().addAll(textArea);

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
