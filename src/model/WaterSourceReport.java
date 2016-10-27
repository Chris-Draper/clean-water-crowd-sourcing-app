package model;

/**
 * Created by Chris on 10/10/2016.
 */
public class WaterSourceReport {

    private String date;
    private String time;
    private int reportNum;
    private String reporterName;
    private double latitude;
    private double longitude;
    private WaterType waterType;
    private WaterCondition condition;

    public enum WaterType {
        BT("Bottled"), WL("Well"), ST("Stream"), LK("Lake"), SP("Spring"), OT("Other");

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

    public WaterSourceReport(String date, String time, int reportNum,
                             String reporterName, double latitude, double longitude,
                             WaterType sourceType, WaterCondition condition) {
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

    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
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

    public WaterType getSourceType() {
        return waterType;
    }

    public void setSourceType(WaterType waterType) {
        this.waterType = waterType;
    }

    public WaterCondition getCondition() {
        return condition;
    }

    public void setCondition(WaterCondition condition) {
        this.condition = condition;
    }

}
