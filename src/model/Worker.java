package model;

/**
 * The worker user class type
 */
class Worker extends User {

    /**
     * The class used to create the worker level user account
     *
     * @param username - the username of the Worker
     * @param id - the id of the Worker
     */
    public Worker(String username, int id) {
        super(username, UserType.Worker, id);
    }

    /**
     *
     * @param username - the username of the Worker
     * @param userType - the user type of the Worker
     * @param id - the id of the Worker
     */
    Worker(String username, UserType userType, int id) {
        super(username, userType, id);
    }
}
