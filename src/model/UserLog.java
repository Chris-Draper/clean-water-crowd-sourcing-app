package model;

//This class was causing errors because its methods are not called within the
//application. The methods have been commented out until the feature is used

/*import java.util.LinkedList;
import java.util.ListIterator;
import java.util.AbstractList;*/

/**
 * UserLog tracks all of the users that have accessed the application
 */
public class UserLog {

    /*private AbstractList<GenericUser> userLog;

    public UserLog() {
        this.userLog = new LinkedList<>();
    }

    /*public void addUser(GenericUser user) {
        userLog.add(user);
    }

    public boolean contains(GenericUser user) {
        return userLog.contains(user);
    }

    public GenericUser getCurrentUser(String userName) {
        GenericUser currentUser = null;
        for(GenericUser genUser: userLog) {
            if(genUser.getUsername().equals(userName)) {
                currentUser = genUser;
            }
        }
        return currentUser;
    }

    public boolean hasAlreadyRegistered(String username) {
        ListIterator<GenericUser> iterator = userLog.listIterator();
        while (iterator.hasNext()) {
            //Checks user names of all already in system against param
            if (iterator.next().getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    } */

}
