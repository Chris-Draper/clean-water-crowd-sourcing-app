package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.WaterSourceReport;
import netscape.javascript.JSObject;

/**
 * Helps to create the google map and display the location pins of water source
 * reports and water purity reports
 */
public class GoogleMapsController implements Initializable,
        MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private MainApplication mainApplication;

    private List<WaterSourceReport> waterSourceList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        final double latCenter = 33.78;
        final double longCenter = -84.40;
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(latCenter, longCenter))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(10);

        map = mapView.createMap(mapOptions);

        System.out.println(waterSourceList.size());

        for (int i = 0; i < waterSourceList.size(); i++) {

            WaterSourceReport waterSource = waterSourceList.get(i);

            MarkerOptions markerOptions = new MarkerOptions();
            LatLong location = new LatLong(waterSource.getLat(),
                    waterSource.getLong());

            System.out.println(location);

            markerOptions.position(location)
                    .visible(Boolean.TRUE)
                    .title("Water Source : " + waterSource.getReportNum());

            System.out.println(markerOptions);

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new
                                InfoWindowOptions();
                        infoWindowOptions.content(waterSource.toHtmlFormat());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }
    }

    public void loadReports() {

        waterSourceList = new ArrayList<>();

        int startReport = mainApplication.getDatabaseConn()
                .getMinSourceReportNum();
        int endReport = mainApplication.getDatabaseConn()
                .getMaxSourceReportNum();

        for (int i = startReport; i <= endReport; i++) {
            waterSourceList.add(mainApplication.getDatabaseConn().getSourceReportInfo(i));
        }

    }

    /**
     * allow for calling back to the mainApplication application
     * code if necessary
     * @param mainApplication   the reference to the FX Application instance
     * */
    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}