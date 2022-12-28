package com.example.holytime.backend.event;

import com.example.holytime.backend.nest.Nest;

import java.util.ArrayList;

public class Event extends Nest {
    public String eventId;
    private String starHour;
    private String endHour;
    private ArrayList<Integer> startDate;
    private ArrayList<Integer> endDate;

    public Event(int id, String nombre, String id1, String[] perfil, String tipo, double geolocalización, double geolocalización1, int duracion, float precio, Object o, Boolean accesible, String starHour, String endHour, ArrayList<Integer> startDate, ArrayList<Integer> endDate) {
        super(nombre, id, perfil, new String[]{tipo}, geolocalización, geolocalización1, duracion, precio, new String[]{}, "", accesible);
        this.eventId = id1;
        this.starHour = starHour;
        this.endHour = endHour;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public void setEventId(String id) {
    }
    //print event
    public void printEvent(){
        System.out.println("Event: " + this.getName());
        System.out.println("Event ID: " + this.eventId);
        System.out.println("Event type id " + this.getId());
        System.out.println("Event Profile: " + this.getProfile()[0]);
        System.out.println("Event Type: " + this.getType()[0]);
        System.out.println("Event Latitude: " + this.getLatitude());
        System.out.println("Event Longitude: " + this.getLongitude());
        System.out.println("Event Visit Time: " + this.getVisitTime());
        System.out.println("Event Price: " + this.getPrice());
        System.out.println("Event Days: " + this.getDays().toString());
        System.out.println("Event Schedule: " + this.getSchedule());
        System.out.println("Event Accesible: " + this.getAccesible());
        System.out.println("Event Star Hour: " + this.starHour);
        System.out.println("Event End Hour: " + this.endHour);
        System.out.println("Event Start Date: " + this.startDate.toString());
        System.out.println("Event End Date: " + this.endDate.toString());
    }

    public String getEventId() {
        return eventId;
    }

    public String getStarHour() {
        return starHour;
    }

    public void setStarHour(String starHour) {
        this.starHour = starHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public ArrayList<Integer> getStartDate() {
        return startDate;
    }

    public void setStartDate(ArrayList<Integer> startDate) {
        this.startDate = startDate;
    }

    public ArrayList<Integer> getEndDate() {
        return endDate;
    }

    public void setEndDate(ArrayList<Integer> endDate) {
        this.endDate = endDate;
    }
}

