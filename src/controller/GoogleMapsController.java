package controller;

/**
 * Created by Chris on 10/17/2016.
 */
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.WaterSourceReport;
import netscape.javascript.JSObject;

public class GoogleMapsController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(33.78, -84.40))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(10);

        map = mapView.createMap(mapOptions);

        ArrayList<WaterSourceReport> sourceList = WaterSourceReportController.getWaterSourceReportList();
        for (WaterSourceReport waterSource: sourceList) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong location = new LatLong(waterSource.getLat(), waterSource.getLong());

            markerOptions.position(location)
                    .visible(Boolean.TRUE)
                    .title("Water Source : " + waterSource.getReportNum());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(waterSource.toString());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }
    }
}