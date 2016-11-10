package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * creates the Water Purity Reports within the application
 */
public class WaterPurityReport {

    private Date actualDate;
    /* The below fields have been made final as the setters used by
    administrator accounts have not yet been implemented within
    the application*/
    private final String time;
    private final int reportNum;
    private final String reporterName;
    private final double latitude;
    private final double longitude;
    private final Condition condition;
    private final double virusPPM;
    private final double contamPPM;

    /**
     * Creates a water purity report object
     *
     * @param time the time the report is submitted
     * @param reportNum the number of the report
     * @param reporterName the name of the person submitting the report
     * @param lat the latitude position of the report location
     * @param longit the longitude position of the report location
     * @param condition the condition of the water
     * @param virusPPM the virus parts per million value of the water
     * @param contamPPM the contaminant parts per million value of the water
     */
    public WaterPurityReport(String time, int reportNum, String reporterName,
                             double lat, double longit,
                             WaterPurityReport.Condition condition,
                             double virusPPM, double contamPPM) {
        this.time = time;
        this.reportNum = reportNum;
        this.reporterName = reporterName;
        this.latitude = lat;
        this.longitude = longit;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contamPPM = contamPPM;
    }

    /**
     * Creates a water purity report object
     *
     * @param date the date of the water purity report
     * @param time the time the report is submitted
     * @param reportNum the number of the report
     * @param reporterName the name of the person submitting the report
     * @param lat the latitude position of the report location
     * @param longit the longitude position of the report location
     * @param condition the condition of the water
     * @param virusPPM the virus parts per million value of the water
     * @param contamPPM the contaminant parts per million value of the water
     */
    public WaterPurityReport(Date date, String time, int reportNum,
                             String reporterName, double lat, double longit,
                             WaterPurityReport.Condition condition,
                             double virusPPM, double contamPPM) {
        this(time, reportNum, reporterName, lat, longit, condition, virusPPM,
                contamPPM
        );
        this.actualDate = date;
    }

    /**
     * Creates a water purity report object
     *
     * @param date the date of the water purity report
     * @param time the time the report is submitted
     * @param reportNum the number of the report
     * @param reporterName the name of the person submitting the report
     * @param lat the latitude position of the report location
     * @param longit the longitude position of the report location
     * @param condition the condition of the water
     * @param virusPPM the virus parts per million value of the water
     * @param contamPPM the contaminant parts per million value of the water
     */
    public WaterPurityReport(String date, String time, int reportNum,
                             String reporterName, double lat, double longit,
                             WaterPurityReport.Condition condition,
                             double virusPPM, double contamPPM) {
        this(time, reportNum, reporterName, lat, longit, condition,
                virusPPM, contamPPM
        );
        String[] arr = date.split("[-]");
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int day = Integer.parseInt(arr[2]);
        this.actualDate = new Date(year - 1900, month, day);
    }

    /**
     *
     * @return a formatted string of the data in a water purity report
     */
    public String toString() {
        return "\n----------------------\n" +
                "Report Number : " + this.reportNum +
                "\nReport Date : " + this.actualDate +
                "\nReport Time : " + this.time +
                "\nReporter Name : " + this.reporterName +
                "\nReport Location Latitude : " + this.latitude +
                "\nReport Location Longitude : " + this.longitude +
                "\nReport Virus : " + this.virusPPM +
                "\nReport contaminant : " + this.contamPPM +
                "\nReport Condition : " + this.condition;
    }

    public enum Condition {
        S("Safe"), T("Treatable"), U("Unsafe");

        Condition (String description) {
            this.description = description;
        }

        /**
         * @return a description stating if the water is safe, treatable,
         * or unsafe
         */
        public String getDescription() {
            return description;
        }

        String description;

        /**
         *
         * @return The description of the water purity report returned
         * as a string
         */
        public String toString() {
            return description;
        }
    }

    /*The water purity reports are not yet displayed in the Google Map so this
    unused method has been commented out */
    /**
     *
     * @return the html formatted string displaying water purity report data in
     * the Google Map
     */
    /*public String toHtmlFormat() {
        return  "<strong>Report Number : " + this.reportNum + "</strong><br>" +
                "<br>Report Date : " + this.date +
                "<br>Report Time : " + this.time +
                "<br>Reporter Name : " + this.reporterName +
                "<br>Report Location Latitude : " + this.latitude +
                "<br>Report Location Longitude : " + this.longitude +
                "<br>Report Virus : " + this.virusPPM +
                "<br>Report contaminant : " + this.contamPPM +
                "<br>Report Condition : " + this.condition;
    }*/

    /**
     *
     * @return the date of the water purity report
     */
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        return dateFormat.format(actualDate);
    }

    /**
     *
     * @return the month of the water purity report as an int
     */
    public int getMonth() {
        return actualDate.getMonth();
    }

    /*public void setDate(String dateTime) {
        this.date = date;
    }*/

    /**
     *
     * @return the time of the water purity report
     */
    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(actualDate);
    }

    /*public void setTime(String dateTime) {
        this.time = time;
    }*/

    /**
     *
     * @return the contaminant ppm of the water purity report
     */
    public double getContamPPM() {
        return contamPPM;
    }

    /*public void setContamPPM(double contamPPM) {
        this.contamPPM = contamPPM;
    }*/

    /**
     *
     * @return the virus ppm of the water purity report
     */
    public double getVirusPPM() {
        return virusPPM;
    }

    /*public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }*/

    /**
     *
     * @return the report number of the water purity report
     */
    public int getReportNum() {
        return reportNum;
    }

    /*public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }*/

    /**
     *
     * @return the name of the person that submitted the water purity report
     */
    public String getReporterName() {
        return reporterName;
    }

    /*public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }*/

    /**
     *
     * @return the latitude position of the water purity report
     */
    public double getLat() {
        return latitude;
    }

    /*public void setLat(double latitude) {
        this.latitude = latitude;
    }*/

    /**
     *
     * @return the longitude position of the water purity report
     */
    public double getLong() {
        return longitude;
    }

    /*public void setLong(double longitude) {
        this.longitude = longitude;
    }*/

    /**
     *
     * @return the condition of the water of the water purity report
     */
    public Condition getCondition() {
        return condition;
    }

    /*public void setCondition(char c) {
        this.condition = condition;
    }*/
}
