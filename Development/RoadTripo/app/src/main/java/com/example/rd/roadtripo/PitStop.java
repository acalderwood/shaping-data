package com.example.rd.roadtripo;

import java.io.Serializable;

/**
 * Created by RD on 11/21/2015.
 */
public class PitStop implements Serializable {

    private String city;
    private String formatted_address;
    private String lat;
    private String lng;
    private String placeId;
    private String places;

    public PitStop(String city, String formatted_address, String lat, String lng, String placeId, String places) {
        this.city = city;
        this.formatted_address = formatted_address;
        this.lat = lat;
        this.lng = lng;
        this.placeId = placeId;
        this.places = places;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
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

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
