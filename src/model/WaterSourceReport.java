package model;

/**
 * The object that stores all of the water source report data
 */
public class WaterSourceReport {

    /* Administrator setters have not yet been implemented so these variables
    have been made final */
    private String date;
    private final String time;
    private final int reportNum;
    private final String reporterName;
    private final double latitude;
    private final double longitude;
    private final WaterType waterType;
    private final WaterCondition condition;

    public enum WaterType {
        BT("Bottled"), WL("Well"), ST("Stream"), LK("Lake"), SP("Spring"),
        OT("Other");

        WaterType(String description) {
            this.description = description;
        }

        String description;

        public String toString() {
            return description;
        }
    }

    public enum WaterCondition {
        W("Waste"), C("Treatable Clear"), M("Treatable Muddy"), P("Potable");

        WaterCondition(String description) {
            this.description = description;
        }

        String description;

        public String toString() {
            return description;
        }
    }

    /**
     * The class that creates all WaterSourceReport objects
     *
     * @param date - the date of the water source report submission
     * @param time - the time of the water source report submission
     * @param reportNum - the number of the water source report
     * @param reporterName - the person submitting the water source report
     * @param latitude - the latitude position of the water source report
     * @param longitude - the longitude position of the water source report
     * @param sourceType - the type of the water source
     * @param condition - the condition that the water source is in
     */
    public WaterSourceReport(String date, String time, int reportNum,
                             String reporterName, double latitude,
                             double longitude, WaterType sourceType,
                             WaterCondition condition) {
        this.date = date;
        this.time = time;
        this.reportNum = reportNum;
        this.reporterName = reporterName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.waterType = sourceType;
        this.condition = condition;
    }

    /**
     *
     * @return a formatted string containing all of the data from the
     * water source reports
     */
    public String toString() {
        return "\n----------------------\n" +
                "Report Number : " + this.reportNum +
                "\nReport Date : " + this.date +
                "\nReport Time : " + this.time +
                "\nReporter Name : " + this.reporterName +
                "\nReport Location Latitude : " + this.latitude +
                "\nReport Location Longitude : " + this.longitude +
                "\nReport Water Type : " + this.waterType +
                "\nReport Condition : " + this.condition;
    }

    /**
     *
     * @return An html formatted string to display water source report data
     * in the google map view
     */
    public String toHtmlFormat() {
        return  "<strong>Report Number : " + this.reportNum + "</strong><br>" +
                "<br>Report Date : " + this.date +
                "<br>Report Time : " + this.time +
                "<br>Reporter Name : " + this.reporterName +
                "<br>Report Location Latitude : " + this.latitude +
                "<br>Report Location Longitude : " + this.longitude +
                "<br>Report Water Type : " + this.waterType +
                "<br>Report Condition : " + this.condition;
    }

    //all the getter and setter methods are for future manager accounts
    /* THE SETTER METHODS THAT ARE UNUSED HAVE BEEN COMMENTED OUT SINCE
    MANAGER ACCOUNT FUNCTIONALITY HAS NOT BEEN IMPLEMENTED */

    /**
     * @return the water source report date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the water source report
     * @param dateTime - the new water source report date
     */
    public void setDate(String dateTime) {
        this.date = dateTime;
    }

    /**
     *
     * @return the time of the water source report
     */
    public String getTime() {
        return time;
    }

    /*public void setTime(String dateTime) {
        this.time = time;
    }*/

    /**
     *
     * @return the number of the water source report
     */
    public int getReportNum() {
        return reportNum;
    }

    /*public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }*/

    /**
     *
     * @return the name of the person that reported the water source
     */
    public String getReporterName() {
        return reporterName;
    }

    /*public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }*/

    /**
     *
     * @return the latitude position of the water source report
     */
    public double getLat() {
        return latitude;
    }

    /*public void setLat(double latitude) {
        this.latitude = latitude;
    }*/

    /**
     *
     * @return the longitude position of the water source report
     */
    public double getLong() {
        return longitude;
    }

    /*public void setLong(double longitude) {
        this.longitude = longitude;
    }*/

    /**
     *
     * @return the type of the water source
     */
    public WaterType getSourceType() {
        return waterType;
    }

    /*public void setSourceType(WaterType waterType) {
        this.waterType = waterType;
    }*/

    /**
     *
     * @return the condition of the water source
     */
    public WaterCondition getCondition() {
        return condition;
    }

    /*public void setCondition(WaterCondition condition) {
        this.condition = condition;
    }*/

}
