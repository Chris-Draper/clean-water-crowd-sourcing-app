package model;

import java.util.Date;

/**
 * The parent class of all other user classes
 */
public abstract class GenericUser {

    private String username;
    private UserType userType;
    private int id;
    private final Date date;

    //all these variables are not used in the current code so they are commented
    //out in case we want to implement this feature in the future

    /*private String fullName;
    private String emailAddress;
    private String homeAddressNum;
    private String homeAddressStreet;
    private String homeAddressCity;
    private String homeAddressState;
    private String homeAddressZip;
    private String phoneNumber;*/

    /**
     * The parent class for all of the users in the application
     *
     * @param username the name of the GenericUser
     * @param userType the type of the GenericUser
     * @param id the id of the GenericUser
     */
    public GenericUser(String username, UserType userType, int id) {
        this.username = username;
        this.userType = userType;
        this.id = id;
        this.date = new Date();
    }

    /**
     *
     * @return The username of the GenericUser
     */
    public String getUsername() { return username; }

    /**
     *
     * @return The id of the GenericUser
     */
    public int getID() { return id; }

    /**
     *
     * @return The type of the GenericUser
     */
    public UserType getUserType() { return userType; }

    /**
     *
     * @return the date the GenericUser was created
     */
    public Date getDate() { return date; }

    //all these variables are not used in the current code so they are commented
    //out in case we want to implement this feature in the future

    /*public void setFullName(String name) {this.fullName = name; }

    public String getFullName() {return fullName; }

    public void setEmailAddress(String emailAddress) {this.emailAddress
            = emailAddress; }

    public String getEmailAddress() {return emailAddress; }

    public void setHomeAddressNum(String homeAddressNum) {
        this.homeAddressNum = homeAddressNum;
    }

    public String getHomeAddressNum() {
        return homeAddressNum;
    }

    public void setHomeAddressStreet(String homeAddressStreet) {
        this.homeAddressStreet = homeAddressStreet;
    }

    public String getHomeAddressStreet() {return homeAddressStreet; }

    public void setHomeAddressCity(String homeAddressCity) {
        this.homeAddressCity = homeAddressCity;
    }

    public String getHomeAddressCity() {
        return homeAddressCity;
    }

    public void setHomeAddressState(String homeAddressState) {
        this.homeAddressState = homeAddressState;
    }

    public String getHomeAddressState() {
        return homeAddressState;
    }

    public void setHomeAddressZip(String homeAddressZip) {
        this.homeAddressZip = homeAddressZip;
    }

    public String getHomeAddressZip() {return homeAddressZip;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {return phoneNumber; }*/

    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else {
            GenericUser user = (GenericUser) o;
            return (this.getUsername().equals(user.getUsername()))
                    && (this.id == user.getID());
        }
    }

}
