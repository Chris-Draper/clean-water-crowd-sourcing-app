package model;

/**
 * Created by Chris on 10/10/2016.
 */
public class WaterSourceReport {

    private String date;
    private String time;
    private String reportNum;
    private String reporterName;
    private double latitude;
    private double longitude;
    private Object waterType;
    private Object condition;

    public enum WaterType { //go back and implement enums
        Bottled, Well, Stream, Lake, Spring, Other
    }

    public enum WaterCondition { //go back and implement enums
        Waste, TreatableClear, TreatableMuddy, Potable
    }

    public WaterSourceReport(String date, String time, String reportNum,
                             String reporterName, double latitude, double longitude,
                             Object sourceType, Object condition) {
        this.date = date;
        this.time = time;
        this.reportNum = reportNum;
        this.reporterName = reporterName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.waterType = sourceType;
        this.condition = condition;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String dateTime) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String dateTime) {
        this.time = time;
    }

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public double getLat() {
        return latitude;
    }

    public void setLat(double latitude) {
        this.latitude = latitude;
    }

    public double getLong() {
        return longitude;
    }

    public void setLong(double longitude) {
        this.longitude = longitude;
    }

    public Object getSourceType() {
        return waterType;
    }

    public void setSourceType(WaterType waterType) {
        this.waterType = waterType;
    }

    public Object getCondition() {
        return condition;
    }

    public void setDateTime(WaterCondition condition) {
        this.condition = condition;
    }

}
