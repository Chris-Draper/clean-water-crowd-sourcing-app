package model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sbuck on 10/21/2016.
 */
public class WaterPurityReport {

    private String date;
    private String time;
    private String reportNum;
    private String reporterName;
    private double latitude;
    private double longitude;
    private Condition condition;
    private double virusPPM;
    private double contamPPM;

    public WaterPurityReport(String reportNum, String reporterName, double lat, double longit, Condition condition, double virusPPM, double contamPPM) {
        this.date = getDate();
        this.time = getTime();
        this.reportNum = reportNum;
        this.reporterName = reporterName;
        this.latitude = lat;
        this.longitude = longit;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contamPPM = contamPPM;
    }
    public String toString() {
        return "\n----------------------\n" +
                "Report Number : " + this.reportNum +
                "\nReport Date : " + this.date +
                "\nReport Time : " + this.time +
                "\nReporter Name : " + this.reporterName +
                "\nReport Location Latitude : " + this.latitude +
                "\nReport Location Longitude : " + this.longitude +
                "\nReport Virus : " + this.virusPPM +
                "\nReport contaminant : " + this.contamPPM +
                "\nReport Condition : " + this.condition;
    }

    public String toHtmlFormat() {
        return  "<strong>Report Number : " + this.reportNum + "</strong><br>" +
                "<br>Report Date : " + this.date +
                "<br>Report Time : " + this.time +
                "<br>Reporter Name : " + this.reporterName +
                "<br>Report Location Latitude : " + this.latitude +
                "<br>Report Location Longitude : " + this.longitude +
                "<br>Report Virus : " + this.virusPPM +
                "<br>Report contaminant : " + this.contamPPM +
                "<br>Report Condition : " + this.condition;
    }
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    public void setDate(String dateTime) {
        this.date = date;
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    public void setTime(String dateTime) {
        this.time = time;
    }

    public double getContamPPM() {
        return contamPPM;
    }

    public void setContamPPM(double contamPPM) {
        this.contamPPM = contamPPM;
    }

    public double getVirusPPM() {
        return virusPPM;
    }

    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    public enum Condition {
        safe, treatable, unsafe
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

    public Object getCondition() {
        return condition;
    }

    public void setDateTime(Condition condition) {
        this.condition = condition;
    }




}
