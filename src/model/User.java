package model;

/**
 * The standard user class - child of GenericUser
 */
public class User extends GenericUser {

    /**
     * Creates a User object - an account with the lowest level rights
     *
     * @param username - the username of the User
     * @param id - the id of the User
     */
    public User(String username, int id) {
        super(username, UserType.User, id);
    }

    /**
     * Creates a User object - an account with the lowest level rights
     *
     * @param username - the username of the User
     * @param userType - the type of the User
     * @param id - the id of the User
     */
    public User(String username, UserType userType, int id) {
        super(username, userType, id);
    }

    /* These methods have not been implemented and have been commented out
    until they are used within the app */

    /* private void submitWaterSourceReport() {}

    private void viewWaterSources() {} */
}
