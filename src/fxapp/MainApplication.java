package fxapp;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import model.DatabaseInterface;
import model.GenericUser;
import model.UserLog;

public class MainApplication extends Application {
    /** the main container for the application window */
    private Stage mainAppScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;

    private GenericUser authenticatedUser;

    private UserLog userLog = new UserLog();

    private DatabaseInterface database;

    /*
    Initialize water source reports ArrayList here
    Create water source report dummy data here
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        mainAppScreen = primaryStage;
        mainAppScreen.setTitle("Clean Water Application");
        initRootLayout(mainAppScreen);

        WaterSourceReportController.initWaterReportList();
        WaterSourceReportController.makeWaterSrcReportDummyData();
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

            if (database == null) {

                // Attempt to connect to database, display error if failure
                try {

                    database = new DatabaseInterface();

                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,
                            "Error connecting to backend database.", ButtonType.OK);
                    alert.showAndWait();
                }
            }

        } catch (IOException e) {
            //error on load, so log it
            System.out.println("Failed to find the fxml file for MainScreen.");
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
            loader.setLocation(MainApplication.class.getResource("../view/HomeScreen_Main.fxml"));
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

    public void switchToRegisterScreen() {
        try {
            // Pointing loader to login screen fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/RegistrationScreen.fxml"));
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
            System.out.println("Failed to find the fxml file for Registration Screen!");
            e.printStackTrace();
        }
    }

    public void updateUserInfo(TextField... fields) {

        int userID = authenticatedUser.getID();
        String[] fieldText = new String[8];

        for (int i=0; i < 8; i++) {
            String curField = fields[i].getText();
            if (curField.equals(""))
                fieldText[i] = "NULL";
            else {
                fieldText[i] = "'" + curField + "'";
            }
        }
         try {
             database.updateProfileInfo(userID, fieldText[0], fieldText[1],
                     fieldText[2], fieldText[3], fieldText[4], fieldText[5],
                     fieldText[6], fieldText[7]);
         } catch (SQLException e) {
             System.out.println("Failed to update user info: " + e);
         }
    }

    public BorderPane getRootLayout() {return rootLayout;}

    public void reloadHomeScreen() {
        initRootLayout(mainAppScreen);
    }

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

    public UserLog getUserlog() {
        return userLog;
    }

    public DatabaseInterface getDatabaseConn() {
        return database;
    }

    public void stop() {
        database.close();
    }


}
