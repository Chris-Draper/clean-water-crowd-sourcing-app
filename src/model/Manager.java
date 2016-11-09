package model;

/**
 * The manager user profile type
 */
public class Manager extends Worker {

    public Manager(String username, int id) {
        super(username, UserType.Manager, id);
    }

    private void viewHistoricalReports() {}

    private void deleteWaterSourceReport() {}

    private void deleteWaterPurityReport() {}
}
