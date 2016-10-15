package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class User extends GenericUser {

    public User(String username, int id) {
        super(username, UserType.User, id);
    }

    public User(String username, UserType userType, int id) {
        super(username, userType, id);
    }

    private void submitWaterSourceReport() {}

    private void viewWaterSources() {}
}
