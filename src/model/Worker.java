package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class Worker extends User {

    public Worker(String username, int id) {
        super(username, UserType.Worker, id);
    }

    public Worker(String username, UserType userType, int id) {
        super(username, userType, id);
    }

    private void submitWaterPurityReport() {}
}
