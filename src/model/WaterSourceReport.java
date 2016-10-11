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

    public enum WaterType {
        Bottled, Well, Stream, Lake, Spring, Other
    }

    public enum WaterCondition {
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

    public static void addReportToList(WaterSourceReport sourceReport) {
        waterSourceReportList.add(sourceReport);
    }

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
