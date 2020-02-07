package com.movilbox.services.entities;


import io.realm.RealmObject;

public class Geo extends RealmObject {

    private String lat;
    private String lng;

    public Geo() {
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
}
