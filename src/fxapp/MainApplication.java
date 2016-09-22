package fxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import controller.WelcomeScreenController;

public class MainApplication extends Application {
    /** the main container for the application window */
    private Stage welcomeScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        welcomeScreen = primaryStage;
        initRootLayout(welcomeScreen);
    }

    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param welcomeScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage welcomeScreen) {
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
            welcomeScreen.setTitle("Clean Water Application");
            welcomeScreen.setScene(scene);
            welcomeScreen.show();

        } catch (IOException e){
            //error on load, so log it
            System.out.println("Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
