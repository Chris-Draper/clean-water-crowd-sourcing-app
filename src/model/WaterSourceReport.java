package model;

import java.util.ArrayList;

/**
 * Created by Chris on 10/10/2016.
 */
public class WaterSourceReport {

    private String dateTime;
    private String reportNum;
    private String reporterName;
    private String location;
    private WaterType waterType;
    private WaterCondition condition;
    private ArrayList<WaterSourceReport> waterSourceReportList = new ArrayList<>();

    public enum WaterType {
        Bottled, Well, Stream, Lake, Spring, Other
    }

    public enum WaterCondition {
        Waste, TreatableClear, TreatableMuddy, Potable
    }

    public WaterSourceReport(String dateTime, String reportNum,
                             String reporterName, String location,
                             WaterType sourceType, WaterCondition condition) {
        this.dateTime = dateTime;
        this.reportNum = reportNum;
        this.reporterName = reporterName;
        this.location = location;
        this.waterType = sourceType;
        this.condition = condition;
    }

    public void addWaterSourceReportToList(WaterSourceReport sourceReport) {
        waterSourceReportList.add(sourceReport);
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public WaterType getSourceType() {
        return waterType;
    }

    public void setSourceType(WaterType waterType) {
        this.waterType = waterType;
    }

    public WaterCondition getCondition() {
        return condition;
    }

    public void setDateTime(WaterCondition condition) {
        this.condition = condition;
    }

}
