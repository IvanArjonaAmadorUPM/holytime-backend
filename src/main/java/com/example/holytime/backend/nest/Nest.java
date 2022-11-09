package com.example.holytime.backend.nest;

public class Nest {

    String name;
    int id;
    String profile [];
    String type [];
    double latitude;
    double longitude;
    int visitTime;
    float price;

    String days [];
    String schedule;

    Boolean accesible;




    public Nest() {

    }
    public Nest(String name, int id, String[] profile, String[] type, double latitude, double longitude, int visitTime, float price , String[] days, String schedule, boolean accesible) {
        this.name = name;
        this.id = id;
        this.profile = profile;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitTime = visitTime;
        this.price = price;
        this.days = days;
        this.schedule = schedule;
        this.accesible = accesible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getProfile() {
        return profile;
    }

    public void setProfile(String[] profile) {
        this.profile = profile;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(int visitTime) {
        this.visitTime = visitTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }
    public Boolean getAccesible() {
        return accesible;
    }

    public void setAccesible(Boolean accesible) {
        this.accesible = accesible;
    }
}
