package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class User extends GenericUser {

    public User(String username, String password) {
        super(username, password, UserType.User);
    }

    public User(String username, String password, UserType userType) {
        super(username, password, userType);
    }

    private void submitWaterSourceReport() {}

    private void viewWaterSources() {}
}
