package com.example.rd.roadtripo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RD on 11/22/2015.
 */
public class PlacesNearby extends AppCompatActivity {

    String allPlaces, placeTitle, placeAddressTitle;
    PitStop currentPitStop;
    String lat, lng, name, place_id, price_level, rating, open_now, vicinity, types;
    private List<Places> places = new ArrayList<>();
    JSONObject jsonObj;

    String[] placeNames = new String[20];
    String[] placeRatings = new String[20];
    String[] placeIds = new String[20];
    String[] placeAddress = new String[20];
    String[] placeLat = new String[20];
    String[] placeLng = new String[20];
    String[] placetype = new String[20];
    int[] placeTypeImg = new int [20];
    String[] placeColors = {"113,193,78", "0,140,221", "247,181,64", "0,177,218", "255,88,68", "0,207,173", "142,68,173", "46,204,113", "242,120,75", "246,71,71", "255,178,48", "195,162,121", "237,108,68", "76,71,67", "113,193,78", "0,140,221", "247,181,64", "0,177,218", "255,88,68", "0,207,173", "231,76,60", "107,185,240", "26,188,156", "243,156,18", "52,73,94", "247,202,24", "142,68,173", "46,204,113", "242,120,75", "246,71,71", "255,178,48", "195,162,121", "237,108,68", "76,71,67", "53,125,125", "208,82,80", "81,132,151", "124,156,73", "215,190,153", "134,136,130"};

    String[] names = {"Depeak", "De0", "asdf"};
    int[] Cars = {R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi, R.drawable.audi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeslist);

        Intent intent = getIntent();
        currentPitStop=(PitStop)getIntent().getSerializableExtra("currentPitStop");
        allPlaces = intent.getStringExtra("allPlaces");
        placeTitle = intent.getStringExtra("placeTitle");
        placeAddressTitle = intent.getStringExtra("placeAddressTitle");

        JSONArray array = null;

        try{
            array = new JSONArray(allPlaces);

            places.removeAll(places);

            for(int i=0; i<array.length(); i++){
                jsonObj  = null;
                jsonObj = array.getJSONObject(i);

                lat = jsonObj.getJSONObject("geometry").getJSONObject("location").getString("lat");
                placeLat[i] = lat + "";

                lng = jsonObj.getJSONObject("geometry").getJSONObject("location").getString("lng");
                placeLng[i] = lng + "";

                name = jsonObj.getString("name");
                placeNames[i] = name + "";

                place_id = jsonObj.getString("place_id");
                placeIds[i] = place_id + "";

                if(jsonObj.has("price_level")){
                    price_level = jsonObj.getString("price_level");
                }
                else{
                    price_level = "";
                }

                if(jsonObj.has("rating")){
                    rating = jsonObj.getString("rating");
                    placeRatings[i] = rating + "";
                }
                else{
                    rating = "";
                    placeRatings[i] = rating + "";
                }

                if(jsonObj.has("open_now")){
                    open_now = jsonObj.getString("open_now");
                }
                else{
                    open_now = "";
                }

                if(jsonObj.has("vicinity")){
                    vicinity = jsonObj.getString("vicinity");
                    placeAddress[i] = vicinity + "";
                }
                else{
                    vicinity = "";
                    placeAddress[i] = vicinity + "";
                }

                types = jsonObj.getString("types");

                if(types.indexOf(',') >= 0){
                    placetype[i] = types.substring(1, types.length() - 1).split(",")[0];
                    System.out.println(placetype[i]);
                    String dynamicImage = placetype[i];
                    placeTypeImg[i] = getResources().getIdentifier("meal_takeaway", "drawable", getPackageName());
                }
                else{
                    placetype[i] = types.substring(1, types.length() - 1);
                    placeTypeImg[i] = getResources().getIdentifier(placetype[i].toString(), "drawable", getPackageName());
                }

                places.add(new Places(lat, lng, name, place_id, price_level, rating, open_now, vicinity, types));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        TextView txtplaceTitle = (TextView)findViewById(R.id.placeTitle);
        txtplaceTitle.setText(placeTitle);

        TextView txtAddressTitle = (TextView)findViewById(R.id.placeAddressTitle);
        txtAddressTitle.setText(placeAddressTitle);

        GridView gridView = (GridView)findViewById(R.id.gridView);
        CustomGridAdapter gridAdapter = new CustomGridAdapter(this, placeNames, placeAddress, placeColors, placeTypeImg);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PlacesNearby.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
