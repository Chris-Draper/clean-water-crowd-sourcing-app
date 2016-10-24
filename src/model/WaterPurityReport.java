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
    private int reportNum;
    private String reporterName;
    private double latitude;
    private double longitude;
    private Condition condition;
    private double virusPPM;
    private double contamPPM;

    public WaterPurityReport(int reportNum, String reporterName, double lat, double longit, Condition condition, double virusPPM, double contamPPM) {
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

    public enum Condition {
        safe, treatable, unsafe
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }

    public double getLongitude() {return longitude;}

    public double getLatitude() {return latitude;}

    public Object getCondition() {return condition;}

    public String getReporterName() {return reporterName;}


}
