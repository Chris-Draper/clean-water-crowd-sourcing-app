package model;

/**
 * The worker user class type
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
