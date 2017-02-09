package com.example.rd.roadtripo;

import java.io.Serializable;

/**
 * Created by RD on 11/22/2015.
 */
public class Places  implements Serializable {

    private String lat;
    private String lng;
    private String name;
    private String place_id;
    private String price_level;
    private String rating;
    private String open_now;
    private String vicinity;
    private String types;

    public Places(String lat, String lng, String name, String place_id, String price_level, String rating, String open_now, String vicinity, String types) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.place_id = place_id;
        this.price_level = price_level;
        this.rating = rating;
        this.open_now = open_now;
        this.vicinity = vicinity;
        this.types = types;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getPrice_level() {
        return price_level;
    }

    public void setPrice_level(String price_level) {
        this.price_level = price_level;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOpen_now() {
        return open_now;
    }

    public void setOpen_now(String open_now) {
        this.open_now = open_now;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
