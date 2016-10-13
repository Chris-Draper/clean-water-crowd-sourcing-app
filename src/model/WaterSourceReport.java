package model;

import java.util.ArrayList;

/**
 * Created by Chris on 10/10/2016.
 */
public class WaterSourceReport {

    private String date;
    private String time;
    private String reportNum;
    private String reporterName;
    private String location;
    private Object waterType;
    private Object condition;
    private static ArrayList<WaterSourceReport> waterSourceReportList = new ArrayList<>();

    public enum WaterType { //go back and implement enums
        Bottled, Well, Stream, Lake, Spring, Other
    }

    public enum WaterCondition { //go back and implement enums
        Waste, TreatableClear, TreatableMuddy, Potable
    }

    public WaterSourceReport(String date, String time, String reportNum,
                             String reporterName, String location,
                             Object sourceType, Object condition) {
        this.date = date;
        this.time = time;
        this.reportNum = reportNum;
        this.reporterName = reporterName;
        this.location = location;
        this.waterType = sourceType;
        this.condition = condition;
    }

    public String toString() {
        return "\n----------------------\n" +
                "Report Number : " + this.reportNum +
                "\nReport Date : " + this.date +
                "\nReport Time : " + this.time +
                "\nReporter Name : " + this.reporterName +
                "\nReport Location : " + this.location +
                "\nReport Water Type : " + this.waterType +
                "\nReport Condition : " + this.condition;
    }

    public static String waterSourceReportsString() {
        String output = "";
        for (int i = 0; i < waterSourceReportList.size(); i++) {
            output += waterSourceReportList.get(i).toString();
        }
        return output;
    }

    public static void createDummyData(ArrayList<WaterSourceReport> waterSourceReports) {
        WaterSourceReport report1 = new WaterSourceReport(
                "10/12/16", "22:16", "2001", "Chris Draper", "Georgia Tech",
                "Lake", "Treatable Clear"
        );
        WaterSourceReport report2 = new WaterSourceReport(
                "10/15/16", "22:16", "2002", "Don Draper", "Georgia Tech",
                "Well", "Potable"
        );
        WaterSourceReport report3 = new WaterSourceReport(
                "10/17/16", "22:16", "2003", "Cynthia Draper", "Georgia Tech",
                "Spring", "Treatable Muddy"
        );
        WaterSourceReport report4 = new WaterSourceReport(
                "9/12/16", "22:16", "2004", "Zach Draper", "Georgia Tech",
                "Bottled", "Waste"
        );
        waterSourceReports.add(report1);
        waterSourceReports.add(report2);
        waterSourceReports.add(report3);
        waterSourceReports.add(report4);
    }

    public static void addReportToList(WaterSourceReport sourceReport) {
        waterSourceReportList.add(sourceReport);
    }

    public static ArrayList<WaterSourceReport> getReportList() {
        return waterSourceReportList;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
