package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class Worker extends OverallUser {

    public Worker(String username, String password) {
        super(username, password, UserType.Worker);
    }
}
