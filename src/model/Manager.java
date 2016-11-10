package model;

/**
 * The manager user profile type
 */
class Manager extends Worker {

    /**
     * Creates a Manager account object
     *
     * @param username the username of the Manager account
     * @param id the id of the Manager account
     */
    public Manager(String username, int id) {
        super(username, UserType.Manager, id);
    }

    /* The methods below have not been implemented and have been commented out
    until they are in use */

    /*private void viewHistoricalReports() {}

    private void deleteWaterSourceReport() {}

    private void deleteWaterPurityReport() {}*/
}
