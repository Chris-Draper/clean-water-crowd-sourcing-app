package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class Manager extends Worker {

    public Manager(String username, String password) {
        super(username, password, UserType.Manager);
    }

    public Manager(String username, String password, UserType userType) {
        super(username, password, userType);
    }

    private void viewHistoricalReports() {}

    private void deleteWaterSourceReport() {}

    private void deleteWaterPurityReport() {}
}
