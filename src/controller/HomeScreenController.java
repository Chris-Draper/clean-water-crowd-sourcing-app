package controller;

import fxapp.MainApplication;

/**
 * Created by nharper32 on 9/24/16.
 */
public class HomeScreenController {

    private MainApplication mainApplication;




    /**
     * allow for calling back to the mainApplication application code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */

    public void setMainApp(MainApplication mainApplication) {this.mainApplication = mainApplication;}
}
