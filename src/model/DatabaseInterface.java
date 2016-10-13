package model;

import com.jcraft.jsch.*;

import java.io.ByteArrayInputStream;
import java.sql.*;
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

    public boolean verifyUser(String username, String password) throws SQLException {

        Statement stmt = null;
        String query = "SELECT position FROM cleanwater.users" +
                " WHERE username='" + username +
                "' AND password='" + password + "'";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next() && rs.getString("position") != "") {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e ) {
            System.out.println(e);
            return false;
        } finally {
            if (stmt != null) { stmt.close(); }
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

    public void close() {
        session.disconnect();
    }

}
