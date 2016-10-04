package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public class Administrator extends GenericUser {

    public Administrator(String username, String password) {
        super(username, password, UserType.Administrator);
    }

    /**
     * Delete actual account of any User of any type
     */
    private void deleteUser() {}

    /**
     * Just stops user from SUBMITTING reports. That user can still view water sources
     */
    private void banUser() {}

    /**
     * Unblock an account that has been locked for incorrect login attempts
     */
    private void unlockUserAccount() {}

    private void viewSecurityLog() {}
}
