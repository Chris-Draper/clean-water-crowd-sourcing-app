package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class User extends OverallUser {

    public User(String username, String password) {
        super(username, password, UserType.User);
    }
}
