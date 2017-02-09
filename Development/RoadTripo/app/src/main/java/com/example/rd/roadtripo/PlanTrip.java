package com.example.rd.roadtripo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RD on 11/21/2015.
 */
public class PlanTrip extends AppCompatActivity {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    ListView pitStopList;
    String allPitstop, startPoint, endPoint, totalTravelDays, totalTravelTime;
    private List<PitStop> pitStop = new ArrayList<>();
    String city, formatted_address, lat, lng, placeId, places, travelTime, travelDays;
    String allPlaces;
    ArrayList<String> nearbyPlaces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantriplist);
        final Context context = this;
        JSONObject jsonObj;
        String test = "";
        Intent intent = getIntent();

        allPitstop = intent.getStringExtra("allPitstop");
        startPoint = intent.getStringExtra("tripFrom");
        endPoint = intent.getStringExtra("tripTo");

        TextView tripFrom = (TextView)findViewById(R.id.tripFrom);
        tripFrom.setText(startPoint);

        TextView tripTo = (TextView)findViewById(R.id.tripTo);
        tripTo.setText(endPoint);

        JSONArray array = null;

        try{
            array = new JSONArray(allPitstop);
            pitStop.removeAll(pitStop);

            for(int i=0; i<array.length(); i++){
                jsonObj  = null;
                jsonObj = array.getJSONObject(i);

                city = jsonObj.getString("city");
                formatted_address = jsonObj.getString("formatted_address");
                lat = jsonObj.getString("lat");
                lng = jsonObj.getString("lng");
                placeId = jsonObj.getString("placeId");
                places = jsonObj.getString("places");

                pitStop.add(new PitStop(city, formatted_address, lat, lng, placeId, places));
            }
            travelDays = array.length() - 1 + "";
            travelTime = (array.length() - 1) * 8 + "+";

            TextView tripTime = (TextView)findViewById(R.id.travelTime);
            tripTime.setText(travelTime);

            TextView tripDays = (TextView)findViewById(R.id.travelDays);
            tripDays.setText(travelDays);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<PitStop> myAdapter = new MyListAdapter();
        pitStopList = (ListView)findViewById(R.id.plantripList);
        pitStopList.setAdapter(myAdapter);
        addListnerOnClick();

        Button btnmap = (Button) findViewById(R.id.btnmap);

        btnmap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent mapIntent = new Intent(context, PitStopMap.class);
                mapIntent.putExtra("tripFrom", endPoint);
                mapIntent.putExtra("tripTo", startPoint);
                mapIntent.putExtra("allPitstop", allPitstop);
                startActivity(mapIntent);
            }

        });
    }

    private class MyListAdapter extends ArrayAdapter<PitStop>{

        public MyListAdapter(){
            super(PlanTrip.this, R.layout.plantrip, pitStop);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.plantrip, parent, false);
            }

            PitStop currentPitStop = pitStop.get(position);

            TextView pitStopCity = (TextView)itemView.findViewById(R.id.pitStopCity);
            pitStopCity.setText(currentPitStop.getCity());

            TextView pitStopAddress = (TextView)itemView.findViewById(R.id.pitStopAddress);
            pitStopAddress.setText(currentPitStop.getFormatted_address());

            return itemView;
        }
    }

    private void addListnerOnClick(){

        final Context places_context = this;
        pitStopList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PitStop currentPitStop = pitStop.get(position);
                String location = currentPitStop.getLat() + "," + currentPitStop.getLng();
                String placeTitle = currentPitStop.getCity();
                String placeAddressTitle = currentPitStop.getFormatted_address();
                String placeType = "point_of_interest,lodging";

                StrictMode.setThreadPolicy(policy);
                URL textUrl;
                final String tripSource = "http://www.roadtripo.com/public/places/"+location + "/" + placeType;

                try {
                    textUrl = new URL(tripSource);
                    nearbyPlaces.clear();
                    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(textUrl.openStream()));
                    String StringBuffer;
                    while ((StringBuffer = bufferReader.readLine()) != null) {
                        nearbyPlaces.add(StringBuffer);
                    }
                    bufferReader.close();

                    allPlaces = nearbyPlaces.toString();

                    allPlaces = allPlaces.substring(1, allPlaces.length() - 1);

                } catch (Exception e) {
                    Log.i("MY ERR", e.getMessage());
                }

                Intent places_intent = new Intent(places_context, PlacesNearby.class);
                places_intent.putExtra("allPlaces", allPlaces);
                places_intent.putExtra("placeTitle", placeTitle);
                places_intent.putExtra("placeAddressTitle", placeAddressTitle);

                Bundle extras = new Bundle();
                extras.putSerializable("currentPitStop", currentPitStop);
                places_intent.putExtras(extras);

                startActivity(places_intent);
            }
        });
    }
}
