package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class Manager extends Worker {

    public Manager(String username, int id) {
        super(username, UserType.Manager, id);
    }


    private void viewHistoricalReports() {}

    private void deleteWaterSourceReport() {}

    private void deleteWaterPurityReport() {}
}
