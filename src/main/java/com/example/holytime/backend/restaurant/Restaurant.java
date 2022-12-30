package com.example.holytime.backend.restaurant;

import com.example.holytime.backend.nest.Nest;

public class Restaurant extends Nest {

    public Restaurant() {
        super();
    }
    public Restaurant(String name, int id, String[] profile, String[] type, double latitude, double longitude, int visitTime, float price, String[] days, String schedule, boolean accesible) {
        super(name, id, profile, type, latitude, longitude, visitTime, price, days, schedule, accesible);
    }
}
