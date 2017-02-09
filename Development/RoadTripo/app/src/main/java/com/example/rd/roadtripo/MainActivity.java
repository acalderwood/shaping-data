package com.example.rd.roadtripo;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    TextView txt_startPoint, txt_endPoint;
    String startPoint, endPoint;
    Button button;
    String allPitstop;
    ArrayList<String> pitStops = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_startPoint = (EditText)findViewById(R.id.startPoint);
        txt_endPoint = (EditText)findViewById(R.id.endPoint);
        addListnerOnButton();
    }

    public void addListnerOnButton(){
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                startPoint = txt_startPoint.getText().toString();
                endPoint = txt_endPoint.getText().toString();

                startPoint = startPoint.replaceAll(" ", "+");
                endPoint = endPoint.replaceAll(" ", "+");

                planTrip(startPoint, endPoint);
            }

        });
    }

    public void planTrip(String startPoint,String endPoint){

        StrictMode.setThreadPolicy(policy);
        final Context context = this;
        URL textUrl;

        final String tripSource = "http://www.roadtripo.com/public/plantrip/"+startPoint + "/" + endPoint;
        try {
            textUrl = new URL(tripSource);
            pitStops.clear();
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(textUrl.openStream()));
            String StringBuffer;
            while ((StringBuffer = bufferReader.readLine()) != null) {
                pitStops.add(StringBuffer);
            }
            bufferReader.close();

            allPitstop = pitStops.toString();
            allPitstop = allPitstop.substring(1, allPitstop.length() - 1);

        } catch (Exception e) {
            Log.i("MY ERR", e.getMessage());
        }

        Intent intent = new Intent(context, PlanTrip.class);
        startPoint = startPoint.replaceAll("'+'", " ");
        endPoint = endPoint.replaceAll("'+'", " ");

        intent.putExtra("tripFrom", endPoint);
        intent.putExtra("tripTo", startPoint);
        intent.putExtra("allPitstop", allPitstop);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
