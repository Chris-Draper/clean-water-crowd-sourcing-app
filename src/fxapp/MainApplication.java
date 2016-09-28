package fxapp;

import controller.HomeScreenController;
import controller.LoginScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import controller.WelcomeScreenController;
import model.GenericUser;

public class MainApplication extends Application {
    /** the main container for the application window */
    private Stage mainAppScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;

    private GenericUser authenticatedUser;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainAppScreen = primaryStage;
        mainAppScreen.setTitle("Clean Water Application");
        initRootLayout(mainAppScreen);
    }

    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainAppScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainAppScreen) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/MainApplication.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            WelcomeScreenController ctrl = loader.getController();
            ctrl.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //mainAppScreen.setTitle("Clean Water Application");
            mainAppScreen.setScene(scene);
            mainAppScreen.show();

        } catch (IOException e){
            //error on load, so log it
            System.out.println("Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }

    }

    public void switchToLoginScreen() {
        try {
            // Pointing loader to login screen fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/LoginScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            LoginScreenController ctrl = loader.getController();
            ctrl.setMainApp(this);

            /** Creating a new scene to display the login screen
             *  Ensures that the content of the existing window is changed
             *  and another window is not created
             */
            Scene scene = new Scene(rootLayout);
            mainAppScreen.setScene(scene);
            mainAppScreen.show();



        } catch (IOException e){
            //error on load, so log it
            System.out.println("Failed to find the fxml file for Log in Screen!!");
            e.printStackTrace();
        }

    }

    public void switchToHomeScreen() {
        try {
            // Pointing loader to login screen fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/HomeScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            HomeScreenController ctrl = loader.getController();
            ctrl.setMainApp(this);

            /** Creating a new scene to display the login screen
             *  Ensures that the content of the existing window is changed
             *  and another window is not created
             */
            Scene scene = new Scene(rootLayout);
            mainAppScreen.setScene(scene);
            mainAppScreen.show();
        } catch (IOException e) {
            System.out.println("Failed to find the fxml file for Home Screen!");
            e.printStackTrace();
        }
    }

    public void reloadHomeScreen() {
        initRootLayout(mainAppScreen);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setAuthenticatedUser(GenericUser authUser) {
        authenticatedUser = authUser;
    }

    public void logoutUser() {
        authenticatedUser = null;
    }
}
