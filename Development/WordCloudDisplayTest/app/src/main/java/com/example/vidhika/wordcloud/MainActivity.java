package com.example.vidhika.wordcloud;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;

public class MainActivity extends Activity {

    Button myButton;
    EditText restaurantID;
    ImageView imageView;
    TextView result;
    Spinner cuisineType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        restaurantID = (EditText) findViewById(R.id.RestaurantIDText);
        myButton = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        Button search = (Button) findViewById(R.id.button);

        search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View V) {
                if (!restaurantID.getText().toString().matches("")) {

                    //hide the keyboard
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(restaurantID.getWindowToken(), 0);

                    try {
                        //get the base64 string
                        String url = "http://wordcloud-env.uq2wbq7bff.us-west-2.elasticbeanstalk.com/b64/" + restaurantID.getText().toString();
                        new RetrieveImageTask().execute(url);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    class RetrieveImageTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {

                URL url = new URL(urls[0]);

                HttpGet httpRequest = null;
                httpRequest = new HttpGet(url.toURI());

                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = (HttpResponse) httpclient
                        .execute(httpRequest);

                HttpEntity entity = response.getEntity();
                BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
                InputStream inputStream = b_entity.getContent();
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    stringBuilder.append(line).append('\n');
                }
                String encodedImage = stringBuilder.toString();

                return encodedImage;

            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(String encodedImage) {
            byte[] encodedBytes = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap img = BitmapFactory.decodeByteArray(encodedBytes, 0, encodedBytes.length);
            imageView.setImageBitmap(img);
        }
    }

}


