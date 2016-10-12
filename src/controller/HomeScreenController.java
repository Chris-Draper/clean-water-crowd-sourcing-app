package controller;

import com.sun.tools.javac.jvm.Gen;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
import sun.net.www.content.text.Generic;

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
    private MenuItem fileLogout;

    @FXML
    private MenuItem fileClose;

    @FXML
    private ToggleButton profileButton;

    @FXML
    private VBox vbox1;

    private VBox vbox2;



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
        if (event.getSource() == profileButton) {
            if (profileButton.isSelected()) {
                rootLayout.setCenter(vbox2);
            } else {
                rootLayout.setCenter(vbox1);
            }
        }
    }

    private void loadVBoxs() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/HomeScreenUser.fxml"));
            vbox2 = loader.load();

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

}
