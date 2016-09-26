package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class Manager extends OverallUser {

    public Manager(String username, String password) {
        super(username, password, UserType.Manager);
    }
}
