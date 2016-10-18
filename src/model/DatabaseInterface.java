package model;

import com.jcraft.jsch.*;
import java.sql.*;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Properties;

/**
 * Created by ChrisPolack on 10/4/16.
 */
public class DatabaseInterface {

    public Connection dbConn;

    // SSH Variables
    static int lport;
    static String rhost;
    static int rport;
    Session session;

    public DatabaseInterface() throws SQLException {

        establishSSH();

        Properties connProperties = new Properties();
        connProperties.put("user", "root");
        connProperties.put("password", "db4321");

        try {
            dbConn = DriverManager.getConnection(
                    ("jdbc:mysql://" + rhost +":" + lport + "/"),
                    connProperties);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
            throw e;
        }
    }

    public void establishSSH() {

        String sshUser = "root";
        String sshPassword = "hackstreet1234";
        String sshHost = "107.170.19.158";
        int port=22;

        try {

            JSch jsch = new JSch();

            session = jsch.getSession(sshUser, sshHost, port);
            session.setPassword(sshPassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            lport = 1234;
            rport = 3306;
            rhost = "localhost";

            int assigned_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);
        } catch (JSchException e) {
            System.out.println("Error establishing SSH tunnel: " + e);
        }
    }

    public GenericUser verifyUser(String username, String password) throws SQLException {

        GenericUser loggedInUser = null;
        Statement stmt = null;
        String query = "SELECT id, position FROM cleanwater.users" +
                " WHERE username='" + username +
                "' AND password='" + password + "'";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String position;
            int id;

            if (rs.next()) {
                id = rs.getInt("id");
                position = rs.getString("position");

                if (position.equals("U")) {
                    loggedInUser = new User(username, id);
                } else if (position.equals("W")) {
                    loggedInUser = new Worker(username, id);
                } else if (position.equals("M")) {
                    loggedInUser = new Manager(username, id);
                } else if (position.equals("A")) {
                    loggedInUser = new Administrator(username, id);
                }
            }
        } catch (SQLException e ) {
            System.out.println(e);
            return null;
        } finally {
            if (stmt != null) { stmt.close(); }
            return loggedInUser;
        }
    }

    public boolean registerUser(String username, String password, UserType position) throws SQLException {

        Statement stmt = null;

        String query =
                "INSERT INTO cleanwater.users" +
                "(username, password, position)" +
                "VALUES ('" + username + "', '" + password + "', '" + position.getCode() + "');";
        try {
            stmt = dbConn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating new user: " + e);
            return false;
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    public void updateProfileInfo(int userID, String fullName, String email,
                                  String addrNum, String street, String zip,
                                  String city, String state, String phoneNum) throws SQLException {

        Statement stmt = null;

        String query =
                "UPDATE cleanwater.users " +
                "SET name=" + fullName + ", " +
                "street_no=" + addrNum + ", " +
                "street=" + street + ", " +
                "email=" + email + ", " +
                "zip=" + zip + ", " +
                "city=" + city + ", " +
                "state=" + state + ", " +
                "phone=" + phoneNum +
                " WHERE id=" + userID + ";";
        try {
            stmt = dbConn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error creating new user: " + e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }

    }

    public String[] getProfileInfo(int userID) throws SQLException {

        String[] profileInfo = new String[8];
        Statement stmt = null;

        String query =
                "SELECT * FROM cleanwater.users " +
                        "WHERE id = " + userID + ";";
        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                profileInfo[0] = rs.getString("name");
                profileInfo[1] = rs.getString("email");
                profileInfo[2] = rs.getString("street_no");
                profileInfo[3] = rs.getString("street");
                profileInfo[4] = rs.getString("zip");
                profileInfo[5] = rs.getString("city");
                profileInfo[6] = rs.getString("state");
                profileInfo[7] = rs.getString("phone");
            }

        } catch (SQLException e) {
            System.out.println("Error updating profile information: " + e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }

        return profileInfo;

    }

    public void close() {
        session.disconnect();
    }

}
