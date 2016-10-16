package model;

import java.util.Date;

/**
 * Created by sbuck on 9/21/2016.
 */
public abstract class GenericUser {

    private String username;
    private UserType userType;
    private int id;
    private String fullName;
    private String emailAddress;
    private String homeAddressNum;
    private String homeAddressStreet;
    private String homeAddressCity;
    private String homeAddressState;
    private String homeAddressZip;
    private String phoneNumber;
    private final Date date;

    public GenericUser(String username, UserType userType, int id) {
        this.username = username;
        this.userType = userType;
        this.id = id;
        this.date = new Date();
    }

    public String getUsername() { return username; }

    public int getID() { return id; }

    public UserType getUserType() { return userType; }

    public Date getDate() { return date; }

    public void setFullName(String name) {this.fullName = name; }

    public String getFullName() {return fullName; }

    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress; }

    public String getEmailAddress() {return emailAddress; }

    public void setHomeAddressNum(String homeAddressNum) {this.homeAddressNum = homeAddressNum; }

    public String getHomeAddressNum() {return homeAddressNum; }

    public void setHomeAddressStreet(String homeAddressStreet) {this.homeAddressStreet = homeAddressStreet; }

    public String getHomeAddressStreet() {return homeAddressStreet; }

    public void setHomeAddressCity(String homeAddressCity) {this.homeAddressCity = homeAddressCity; }

    public String getHomeAddressCity() {return homeAddressCity; }

    public void setHomeAddressState(String homeAddressState) {this.homeAddressState = homeAddressState; }

    public String getHomeAddressState() {return homeAddressState; }

    public void setHomeAddressZip(String homeAddressZip) {this.homeAddressZip = homeAddressZip; }

    public String getHomeAddressZip() {return homeAddressZip; }

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }

    public String getPhoneNumber() {return phoneNumber; }

    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else {
            GenericUser user = (GenericUser) o;
            return (this.getUsername().equals(user.getUsername()) && this.id == user.getID());
        }
    }

}
