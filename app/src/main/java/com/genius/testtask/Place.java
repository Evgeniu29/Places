package com.genius.testtask;


public class Place {
    private String id;
    private String name;
    private String lat;
    private String lng;

    public  Place(){}
    public Place(String id,String name,String lat,String lng){
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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