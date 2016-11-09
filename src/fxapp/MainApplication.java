package fxapp;

import controller.LoginScreenController;
import controller.WaterPurityReportController;
import controller.WaterSourceReportController;
import controller.WelcomeScreenController;
import controller.HomeScreenController;
import controller.RegistrationScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import model.DatabaseInterface;
import model.GenericUser;
import model.UserLog;

/**
 * Launches the main application
 */
public class MainApplication extends Application {
    /** the main container for the application window */
    private Stage mainAppScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;

    private GenericUser authenticatedUser;

    private UserLog userLog = new UserLog();

    private DatabaseInterface database;

    /**
     *
     * @param primaryStage the stage the application is displayed on
     * @throws Exception catches any generic errors while starting the app
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        mainAppScreen = primaryStage;
        mainAppScreen.setTitle("Clean Water Application");
        initRootLayout(mainAppScreen);

        WaterSourceReportController.initWaterReportList();
        WaterPurityReportController.initWaterPurityList();
    }

    /**
     * Initialize the main screen for the application.  Most other views
     * will be shown in this screen.
     *
     * @param mainAppScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainAppScreen) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class
                    .getResource("../view/MainApplication.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            WelcomeScreenController ctrl = loader.getController();
            ctrl.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            //mainAppScreen.setTitle("Clean Water Application");
            mainAppScreen.setScene(scene);
            mainAppScreen.show();

            if (database == null) {
                // Attempt to connect to database, display error if failure
                try {
                    database = new DatabaseInterface();
                } catch (SQLException e) {
                    Alert alert = new Alert(
                        Alert.AlertType.ERROR,
                        "Error connecting to backend database.",
                        ButtonType.OK
                    );
                    alert.showAndWait();
                }
            }
        } catch (IOException e) {
            //error on load, so log it
            System.out.println("Failed to find the fxml file for MainScreen.");
            e.printStackTrace();
        }
    }

    /**
     * switches to the login screen of the main app
     */
    public void switchToLoginScreen() {
        try {
            // Pointing loader to login screen fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class
                    .getResource("../view/LoginScreen.fxml"));
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
            System.out.println("Failed to find the fxml file for Log"
                    + " in Screen!!");
            e.printStackTrace();
        }

    }

    /**
     * switches to the home screen of the main app
     */
    public void switchToHomeScreen() {
        try {
            // Pointing loader to login screen fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class
                    .getResource("../view/HomeScreen_Main.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            HomeScreenController ctrl = loader.getController();
            ctrl.setMainApp(this);

            /** Creating a new scene to display the Home Screen
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

    /**
     * switches to the registration screen of the main app
     */
    public void switchToRegisterScreen() {
        try {
            // Pointing loader to login screen fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class
                    .getResource("../view/RegistrationScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            RegistrationScreenController ctrl = loader.getController();
            ctrl.setMainApp(this);

            /** Creating a new scene to display the Register Screen
             *  Ensures that the content of the existing window is changed
             *  and another window is not created
             */
            Scene scene = new Scene(rootLayout);
            mainAppScreen.setScene(scene);
            mainAppScreen.show();
        } catch (IOException e) {
            System.out.println("Failed to find the fxml file for Registration"
                    + " Screen!");
            e.printStackTrace();
        }
    }

    /**
     * Updates the user info with data from the database on user login
     * @param fields the text fields of the user
     */
    public void updateUserInfo(TextField... fields) {

        int userID = authenticatedUser.getID();
        String[] fieldText = new String[8];

        for (int i=0; i < 8; i++) {
            String curField = fields[i].getText();
            if (curField.equals("")) {
                fieldText[i] = "NULL";
            }
            else {
                fieldText[i] = "'" + curField + "'";
            }
        }
         database.updateProfileInfo(userID, fieldText[0], fieldText[1],
                 fieldText[2], fieldText[3], fieldText[4], fieldText[5],
                 fieldText[6], fieldText[7]);
    }

    /**
     *
     * @return the rootLayout of the main application
     */
    public BorderPane getRootLayout() {return rootLayout;}

    /**
     * reloads the home screen of the main app
     */
    public void reloadHomeScreen() {
        initRootLayout(mainAppScreen);
    }

    /**
     * The main method that launches the app
     *
     * @param args arguments that change the conditions of launching
     *             the main app
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void setAuthenticatedUser(GenericUser authUser) {
        authenticatedUser = authUser;
    }

    public GenericUser getAuthenticatedUser() {return authenticatedUser;}

    public void logoutUser() {
        authenticatedUser = null;
    }

    public UserLog getUserLog() {
        return userLog;
    }

    public DatabaseInterface getDatabaseConn() {
        return database;
    }

    @Override
    public void stop() {
        database.close();
    }

}
