package model;

/**
 * The standard user class - child of GenericUser
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
