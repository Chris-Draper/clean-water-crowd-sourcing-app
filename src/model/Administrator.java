package model;

/**
 * The class for an Administrator level user account
 */
class Administrator extends GenericUser {

    /**
     * Creates an administrator user object
     *
     * @param username the login name of the user
     * @param id the id number of the user
     */
    public Administrator(String username, int id) {
        super(username, UserType.Administrator, id);
    }

    /* These methods in the administrator class have been commented out. They
    will be implemented once the administrator setter functionality is created
    within the application */
    /**
     * Delete actual account of any User of any type
     */
    /*private void deleteUser() {}*/

    /**
     * Just stops user from SUBMITTING reports. That user can still view water
     * sources
     */
    /*private void banUser() {}*/

    /**
     * Unblock an account that has been locked for incorrect login attempts
     */
    /*private void unlockUserAccount() {}*/

    /*private void viewSecurityLog() {}*/
}
