package model;

import java.sql.*;
import java.util.Properties;


/**
 * Created by ChrisPolack on 10/4/16.
 */
public class DatabaseInterface {

    public Connection dbConn;

    public DatabaseInterface() throws SQLException {

        Properties connProperties = new Properties();
        connProperties.put("user", "root");
        connProperties.put("password", "root1234");

        try {
            dbConn = DriverManager.getConnection(
                    "jdbc:mysql://cleanwaterdb.cmfodw3mwnpr.us-west-2.rds.amazonaws.com:3306/",
                    connProperties);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
            throw e;
        }
    }

    public boolean verifyUser(String username, String password) throws SQLException {

        Statement stmt = null;
        String query = "SELECT role FROM cleanwaterapp.users" +
                " WHERE username='" + username +
                "' AND password='" + password + "'";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getString("role") != "") {
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



}
