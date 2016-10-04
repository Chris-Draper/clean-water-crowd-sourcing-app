package model;

import java.util.Date;

/**
 * Created by sbuck on 9/21/2016.
 */
public abstract class GenericUser {

    private String username;
    private String password;
    private UserType userType;
    private String fullName;
    private String emailAddress;
    private String homeAddress;
    private String phoneNumber;
    private final Date date;

    public GenericUser(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.date = new Date();
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public UserType getUserType() { return userType; }

    public Date getDate() { return date; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setFullName(String name) {this.fullName = name; }

    public String getFullName() {return fullName; }

    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress; }

    public String getEmailAddress() {return emailAddress; }

    public void setHomeAddress(String homeAddress) {this.homeAddress = homeAddress; }

    public String getHomeAddress() {return homeAddress; }

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }

    public String getPhoneNumber() {return phoneNumber; }



    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else {
            GenericUser user = (GenericUser) o;
            return this.getUsername().equals(user.getUsername()) && this.getPassword().equals(user.getPassword());
        }
    }

}
