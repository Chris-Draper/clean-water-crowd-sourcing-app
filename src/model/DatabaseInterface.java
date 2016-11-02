package model;

import com.jcraft.jsch.*;
import java.sql.*;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.DoubleSummaryStatistics;
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

    /**
     * Creates a new connection interface that the main application
     * will use to communicate with the database backend.
     *
     * @throws SQLException
     */
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

    private void establishSSH() {

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

    /**
     * Queries the database with the provided username and
     * password to attempt to login.
     *
     * @param username username of the user attempting to login
     * @param password password of the user attempting to login
     * @return A user object of the appropriate role if the username and password
     * are valid, and null otherwise.
     */
    public GenericUser verifyUser(String username, String password) {

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
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
            return loggedInUser;
        }
    }

    /**
     * Queries the database to see if a username
     * already exists in the system.
     *
     * @param username username to check the existence of
     * @return true if the username already exists, false otherwise
     */
    public boolean hasAlreadyRegistered(String username) {

        Statement stmt = null;
        String query = "SELECT id FROM cleanwater.users" +
                " WHERE username='" + username + "'";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e ) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }

        return false;
    }

    /**
     * Registers the provided user in the database.
     *
     * @param username username of the user attempting to register
     * @param password password that will be associated with the account
     * @param position role of the account to be registered
     * @return true if successfully registered, false otherwise
     */
    public boolean registerUser(String username, String password, UserType position) {

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
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }
    }

    /**
     * Updates the profile information for the provided user.
     *
     * @param userID ID number associated with the current user
     * @param fullName Full name (First Last) for the current user
     * @param email Email address of the current user
     * @param addrNum Address number for the current user
     * @param street Street name for the current user
     * @param zip Zip code for the address of the current user
     * @param city City for the address of the current user
     * @param state State for the address of the current user
     * @param phoneNum Phone number of the current user
     */
    public void updateProfileInfo(int userID, String fullName, String email,
                                  String addrNum, String street, String zip,
                                  String city, String state, String phoneNum) {

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
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }

    }

    /**
     * Queries the database to get the profile information
     * associated with the given user.
     *
     * @param userID ID number associated with the current user
     * @return String array containing all available information
     * for the current user
     */
    public String[] getProfileInfo(int userID) {

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
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }

        return profileInfo;

    }

    /**
     * Inserts the given Water Source Report information into the
     * database.
     *
     * @param date Date at which the report was submitted
     * @param time Time at which the report was submitted
     * @param num System-generated ID number for the report
     * @param reporter username of the user who submitted the report
     * @param latitude latitude of the water source
     * @param longitude longitude of the water source
     * @param type type of the water source
     * @param condition condition of the water source
     * @return whether or not the report was successfully stored
     */
    public boolean submitWaterSourceReport(String date, String time, String num,
                                           String reporter, Double latitude, Double longitude,
                                           String type, String condition) {

        Statement stmt = null;

        String query =
                "INSERT INTO cleanwater.source_reports" +
                        " (`id`, `date`, `time`, `reporter`, `latitude`, `longitude`, `type`, `condition`) " +
                        "VALUES (" + num + ", '" + date + "', '" + time +
                        "', '" + reporter + "', " + latitude + ", " + longitude +
                        ", '" + type + "', '" + condition + "');";
        try {
            stmt = dbConn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating new water report: " + e);
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement.");
                }
            }
        }
    }

    /**
     * Queries the database to get the largest ID
     * number of all water source reports in the database.
     *
     * @return maximum ID number of all water source reports
     */
    public int getMaxSourceReportNum() {

        Statement stmt = null;
        String query = "SELECT max(id) FROM cleanwater.source_reports";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int maxNum;

            if (rs.next()) {
                maxNum = rs.getInt("max(id)");
                return maxNum;
            } else {
                System.out.println("Error");
                return 0;
            }
        } catch (SQLException e ) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }

        return 0;

    }

    /**
     * Queries the database to get the smallest ID
     * number of all water source reports in the database.
     *
     * @return minimum ID number of all water source reports
     */
    public int getMinSourceReportNum() {

        Statement stmt = null;
        String query = "SELECT min(id) FROM cleanwater.source_reports";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int minNum;

            if (rs.next()) {
                minNum = rs.getInt("min(id)");
                return minNum;
            } else {
                System.out.println("Error");
                return 0;
            }
        } catch (SQLException e ) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }

        return 0;

    }

    /**
     * Queries the database to obtain the details of a
     * given water source report.
     *
     * @param reportNum ID number associated with the report
     * @return WaterSourceReport object containing all of the
     * report details available.
     */
    public WaterSourceReport getSourceReportInfo(int reportNum) {

        Statement stmt = null;

        String query =
                "SELECT * FROM cleanwater.source_reports " +
                        "WHERE id = " + reportNum + ";";
        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String date;
            String time;
            String reporter;
            Double latitude;
            Double longitude;
            WaterSourceReport.WaterType type;
            WaterSourceReport.WaterCondition condition;

            if (rs.next()) {
                date = rs.getString("date");
                time = rs.getString("time");
                reporter = rs.getString("reporter");
                latitude = rs.getDouble("latitude");
                longitude = rs.getDouble("longitude");
                type = WaterSourceReport.WaterType.valueOf(rs.getString("type"));
                condition = WaterSourceReport.WaterCondition.valueOf(rs.getString("condition"));

                WaterSourceReport report = new WaterSourceReport(date, time, reportNum, reporter,
                        latitude, longitude, type, condition);

                return report;
            }

        } catch (SQLException e) {
            System.out.println("Error updating profile information: " + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection");
                }
            }
        }

        return null;
    }

    /**
     * Inserts the given Water Purity Report information into the
     * database.
     *
     * @param date Date at which the report was submitted
     * @param time Time at which the report was submitted
     * @param num System-generated ID number for the report
     * @param reporter username of the user who submitted the report
     * @param latitude latitude of the purity report
     * @param longitude longitude of the purity report
     * @param condition condition of the water
     * @param virus ppm of viruses in the water
     * @param contaminant ppm of contaminants in the water
     * @return whether or not the report was successfully stored
     */
    public boolean submitWaterPurityReport(String date, String time, String num,
                                           String reporter, Double latitude, Double longitude,
                                           String condition, String virus, String contaminant) {

        Statement stmt = null;

        String query =
                "INSERT INTO cleanwater.purity_reports" +
                        " (`id`, `date`, `time`, `reporter`, `latitude`, `longitude`, `virus`, `contaminant`, `condition`) " +
                        "VALUES (" + num + ", '" + date + "', '" + time +
                        "', '" + reporter + "', " + latitude + ", " + longitude +
                        ", " + virus + ", " + contaminant + ", '" + condition + "');";

        try {
            stmt = dbConn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating new water purity report: " + e);
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement.");
                }
            }
        }
    }

    /**
     * Queries the database to get the smallest ID
     * number of all water purity reports in the database.
     *
     * @return minimum ID number of all water purity reports
     */
    public int getMinPurityReportNum() {

        Statement stmt = null;
        String query = "SELECT min(id) FROM cleanwater.purity_reports";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int minNum;

            if (rs.next()) {
                minNum = rs.getInt("min(id)");
                return minNum;
            } else {
                System.out.println("Error");
                return 0;
            }
        } catch (SQLException e ) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }

        return 0;

    }

    /**
     * Queries the database to get the largest ID
     * number of all water purity reports in the database.
     *
     * @return maximum ID number of all water purity reports
     */
    public int getMaxPurityReportNum() {

        Statement stmt = null;
        String query = "SELECT max(id) FROM cleanwater.purity_reports";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int maxNum;

            if (rs.next()) {
                maxNum = rs.getInt("max(id)");
                return maxNum;
            } else {
                System.out.println("Error");
                return 0;
            }
        } catch (SQLException e ) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close statement.");
                }
            }
        }

        return 0;

    }

    /**
     * Queries the database to obtain the details of a
     * given water source report.
     *
     * @param reportNum ID number associated with the report
     * @return WaterSourceReport object containing all of the
     * report details available.
     */
    public WaterPurityReport getPurityReportInfo(int reportNum) {

        Statement stmt = null;

        String query =
                "SELECT * FROM cleanwater.purity_reports " +
                        "WHERE id = " + reportNum + ";";
        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String date;
            String time;
            String reporter;
            Double latitude;
            Double longitude;
            WaterPurityReport.Condition condition;
            int virusPPM;
            int contaminantPPM;

            if (rs.next()) {
                date = rs.getString("date");
                time = rs.getString("time");
                reporter = rs.getString("reporter");
                latitude = rs.getDouble("latitude");
                longitude = rs.getDouble("longitude");
                condition = WaterPurityReport.Condition.valueOf(rs.getString("condition"));
                virusPPM = rs.getInt("virus");
                contaminantPPM = rs.getInt("contaminant");
                WaterPurityReport report = new WaterPurityReport(date, time, reportNum, reporter,
                        latitude, longitude, condition, virusPPM, contaminantPPM);

                return report;
            }

        } catch (SQLException e) {
            System.out.println("Error updating profile information: " + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection");
                }
            }
        }

        return null;
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        session.disconnect();
    }

}
