package com.example.rd.roadtripo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by RD on 11/22/2015.
 */
public class CustomGridAdapter extends BaseAdapter{

    private Context c;
    String[] placeNames;
    String[] placeAddress;
    String[] placeColors;
    String[] placetype;
    int[] images;

    public CustomGridAdapter(Context c, String[] city, String[] address, String[] colors,int[] type){
        this.c = c;
        this.placeNames = city;
        this.placeAddress = address;
        this.placeColors = colors;
        this.images = type;
    }

    @Override
    public int getCount() {
        return placeNames.length;
    }

    @Override
    public Object getItem(int position) {
        return placeNames[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.places, null);

        }

        int r = Integer.parseInt(placeColors[position].split(",")[0]);
        int g = Integer.parseInt(placeColors[position].split(",")[1]);
        int b = Integer.parseInt(placeColors[position].split(",")[2]);

        convertView.setPadding(1, 1, 1, 1);
        convertView.setBackgroundColor(Color.rgb(r, g, b));
        TextView palceName = (TextView) convertView.findViewById(R.id.placeName);
        TextView palceAddress = (TextView) convertView.findViewById(R.id.placeAddress);
        ImageView imgview = (ImageView) convertView.findViewById(R.id.placeImage);


        palceName.setText(placeNames[position]);
        palceAddress.setText(placeAddress[position]);
        imgview.setImageResource(images[position]);

        return convertView;
    }
}