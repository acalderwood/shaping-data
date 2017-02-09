package com.example.rd.roadtripo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RD on 11/23/2015.
 */
public class PitStopMap extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    String allPitstop, startPoint, endPoint;
    private JSONArray pitstopArray = null;
    private int pitStopPosition = 0;
    private double initLat = 41.889;
    private double initLng = -87.622;
    private double prevLat = 41.889;
    private double prevLng = -87.622;
    JSONObject jsonObj;
    String lat = "41.889",lng = "-87.622";
    LatLng MOUNTAIN_VIEW;
    Button btnprev, btnnext;
    Marker marker;
    int placeLoc = 0;
    private static final LatLng CHICAGO = new LatLng(41.8369,-87.6847);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pitstopmap);

        final Context context = this;
        Intent mapIntent = getIntent();
        startPoint = mapIntent.getStringExtra("tripFrom");
        endPoint = mapIntent.getStringExtra("tripTo");
        allPitstop = mapIntent.getStringExtra("allPitstop");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView mapPlaceTitle = (TextView)findViewById(R.id.mapPlaceTitle);
        TextView mapAddressTitle = (TextView)findViewById(R.id.mapAddressTitle);
        try {
            pitstopArray = new JSONArray(allPitstop);
            JSONArray array = getPitstopArray();
            jsonObj = array.getJSONObject(getPitStopPosition());
            mapPlaceTitle.setText(jsonObj.getString("city"));
            mapAddressTitle.setText(jsonObj.getString("formatted_address"));
            setInitLat(Double.parseDouble(jsonObj.getString("lat")));
            setInitLng(Double.parseDouble(jsonObj.getString("lng")));
            setPitStopPosition(1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public double getPrevLng() {
        return prevLng;
    }

    public void setPrevLng(double prevLng) {
        this.prevLng = prevLng;
    }

    public double getPrevLat() {
        return prevLat;
    }

    public void setPrevLat(double prevLat) {
        this.prevLat = prevLat;
    }

    public double getInitLat(){
        return initLat;
    }

    public void setInitLat(double initLat){
        this.initLat = initLat;
    }

    public double getInitLng(){
        return initLng;
    }

    public void setInitLng(double initLng){
        this.initLng = initLng;
    }

    public JSONArray getPitstopArray() {
        return pitstopArray;
    }

    public int getPitStopPosition(){
        return  pitStopPosition;
    }

    public void setPitStopPosition(int pitStopPosition){
        this.pitStopPosition = pitStopPosition;
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng mapCenter = new LatLng(getInitLat(), getInitLng());
        setPrevLat(getInitLat());
        setPrevLng(getInitLng());

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));

        // Flat markers will rotate when the map is rotated,
        // and change perspective when the map is tilted.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .position(mapCenter)
                .flat(false)
                .rotation(0));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(mapCenter)
                .zoom(13)
                .bearing(90)
                .build();

        // Animate the change in camera view over 2 seconds
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                2000, null);

        btnnext = (Button) findViewById(R.id.btnnext);

        btnnext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(getPitstopArray().length() - 1 < getPitStopPosition()){
                    setPitStopPosition(0);
                }

                JSONArray array = getPitstopArray();
                String lat = "41.889", lng = "-87.624";
                TextView mapPlaceTitle = (TextView)findViewById(R.id.mapPlaceTitle);
                TextView mapAddressTitle = (TextView)findViewById(R.id.mapAddressTitle);

                try {
                    jsonObj = array.getJSONObject(getPitStopPosition());
                    lat = jsonObj.getString("lat");
                    lng = jsonObj.getString("lng");
                    mapPlaceTitle.setText(jsonObj.getString("city"));
                    mapAddressTitle.setText(jsonObj.getString("formatted_address"));
                    setPitStopPosition(getPitStopPosition() + 1);
                    System.out.println("Prev Stop" + (getPitStopPosition() - 2));
                    if((getPitStopPosition() - 2) == -1){
                        setPrevLat(Double.parseDouble(array.getJSONObject(getPitStopPosition() - 1).getString("lat")));
                        setPrevLng(Double.parseDouble(array.getJSONObject(getPitStopPosition() - 1).getString("lng")));
                    }
                    else{
                        setPrevLat(Double.parseDouble(array.getJSONObject(getPitStopPosition() - 2).getString("lat")));
                        setPrevLng(Double.parseDouble(array.getJSONObject(getPitStopPosition() - 2).getString("lng")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LatLng mapCenter = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                LatLng prevmapCenter = new LatLng(getPrevLat(), getPrevLng());
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(prevmapCenter, 13));

                // Zoom in, animating the camera.
                map.animateCamera(CameraUpdateFactory.zoomIn());

                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                CameraPosition cameraPosition = CameraPosition.builder()
                        .target(mapCenter)
                        .zoom(13)
                        .bearing(90)
                        .tilt(30)
                        .build();

                // Animate the change in camera view over 2 seconds
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                // Flat markers will rotate when the map is rotated,
                // and change perspective when the map is tilted.
                map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                        .position(mapCenter)
                        .flat(false)
                        .rotation(0));
            }

        });


    }
}
