package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class Worker extends User {

    public Worker(String username, String password) {
        super(username, password, UserType.Worker);
    }

    public Worker(String username, String password, UserType userType) {
        super(username, password, userType);
    }

    private void submitWaterPurityReport() {}
}
