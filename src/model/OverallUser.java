package model;

/**
 * Created by sbuck on 9/21/2016.
 */
public abstract class OverallUser {

    private String username;
    private String password;
    private UserType userType;

    public OverallUser(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public UserType getUserType() { return userType; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

}
