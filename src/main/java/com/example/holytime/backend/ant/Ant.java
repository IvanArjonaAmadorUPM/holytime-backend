package com.example.holytime.backend.ant;

import java.util.Arrays;

public class Ant {
    private boolean accesible;
    private String userEmail;
    private String day;
    private String hour;
    private double latitude;
    private double longitude;
    private Integer time;
    private String [] events;
    private String [] food;
    private String [] preferences;
    private String [] profiles;

    public Ant() {
    }
    public Ant(
               String hour,
               String day,
               Integer time,
               double latitude,
               double longitude,
               String[] preferences,
               boolean accesible,
               String[] food,
               String[] events,
               String[] profiles,
               String userEmail
    ) {
        System.out.println(latitude);
        this.accesible = accesible;
        this.userEmail = userEmail;
        this.day = day;
        this.hour = hour;
        this.latitude =(double) latitude;
        this.longitude =(double) longitude;
        this.time = time;
        this.events = events;
        this.food = food;
        this.preferences = preferences;
        this.profiles = profiles;
    }

    public boolean isAccesible() {
        return accesible;
    }

    public void setAccesible(boolean accesible) {
        this.accesible = accesible;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String[] getEvents() {
        return events;
    }

    public void setEvents(String[] events) {
        this.events = events;
    }

    public String[] getFood() {
        return food;
    }

    public void setFood(String[] food) {
        this.food = food;
    }

    public String[] getPreferences() {
        return preferences;
    }

    public void setPreferences(String[] preferences) {
        this.preferences = preferences;
    }

    public String[] getProfile() {
        return profiles;
    }

    public void setProfile(String[] profile) {
        this.profiles = profile;
    }

    @Override
    public String toString() {

        return "Ant{" +
                "accesible=" + accesible +
                ", userEmail='" + userEmail + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", time=" + time +
                ", events=" + Arrays.toString(events) +
                ", food=" + Arrays.toString(food) +
                ", preferences=" + Arrays.toString(preferences) +
                ", profiles=" + Arrays.toString(profiles) +
                '}';
    }
}
