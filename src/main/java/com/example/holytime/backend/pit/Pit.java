package com.example.holytime.backend.pit;

import com.example.holytime.backend.nest.Nest;

public class Pit extends Nest {
    private int fame;
    private int importance;
    String specialHour [];

    //constructor
    public Pit() {
        super();
    }
    public Pit(String name, int id, String[] profile, String[] type, double latitude, double longitude, int visitTime, float price,String [] days, String schedule,boolean  accesible, int fame, int importance, String[] specialHour) {
        super(name, id, profile, type, latitude, longitude, visitTime, price, days, schedule, accesible);
        this.fame = fame;
        this.importance = importance;
        this.specialHour = specialHour;
    }


    //getters and setters
    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String[] getSpecialHour() {
        return specialHour;
    }

    public void setSpecialHour(String[] specialHour) {
        this.specialHour = specialHour;
    }
}
