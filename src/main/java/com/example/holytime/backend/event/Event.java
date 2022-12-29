package com.example.holytime.backend.event;

import com.example.holytime.backend.ant.Ant;
import com.example.holytime.backend.nest.Nest;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event extends Nest {
    public String eventId;
    private String starHour;
    private String endHour;
    private ArrayList<Integer> startDate;
    private ArrayList<Integer> endDate;
    public boolean visited = false;

    public int waitingTime = 0;

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

    public boolean isDayOpen() {
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        long startDay = (long) this.startDate.toArray()[0];
        long startMonth = (long) this.startDate.toArray()[1];
        long startYear = (long) this.startDate.toArray()[2];

        long endDay = (long) this.endDate.toArray()[0];
        long endMonth = (long) this.endDate.toArray()[1];
        long endYear = (long) this.endDate.toArray()[2];

        if(currentYear >= startYear && currentYear <= endYear){
            if(currentMonth >= startMonth && currentMonth <= endMonth){
                if(currentDay >= startDay && currentDay <= endDay){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkOpenHours(String currentHour, Ant ant) {

        if(this.starHour.equals("Todo el día")){
            return true;
        }
        int currentHourTime = Integer.parseInt(currentHour.split(":")[0]);
        int currentMinuteTime = Integer.parseInt(currentHour.split(":")[1]);
        int eventStarHourTime = Integer.parseInt(this.starHour.split(":")[0]);
        int eventStarMinuteTime = Integer.parseInt(this.starHour.split(":")[1]);

        //check if there are less than 60 minutes between currenTime and eventStarTime
        int timeDifference = ((eventStarHourTime*60)+eventStarMinuteTime) - ((currentHourTime*60)+currentMinuteTime);
        if( timeDifference < 60 && timeDifference > 0){
            this.waitingTime = timeDifference;
            return true;
        }
        return false;
    }


}

