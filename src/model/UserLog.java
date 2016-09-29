package model;

import java.util.LinkedList;

/**
 * Created by nharper32 on 9/29/16.
 */
public class UserLog {

    private LinkedList<GenericUser> userLog;

    public UserLog() {
        this.userLog = new LinkedList<>();
        this.addDummy();
    }

    public void addUser(GenericUser user) {
        userLog.add(user);
    }

    public void addDummy() {
        addUser(new Manager("nhaper32", "harper285"));
        addUser(new Worker("cdraper", "draper"));
        addUser(new User("sbuckingham", "buckingham"));
        addUser(new Administrator("cpolack", "polack"));
    }

    public boolean contains(GenericUser user) {
        return userLog.contains(user);
    }



}
