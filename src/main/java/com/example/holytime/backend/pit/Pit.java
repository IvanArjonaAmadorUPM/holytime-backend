package com.example.holytime.backend.pit;

import com.example.holytime.backend.Math.DistanceCalculator;
import com.example.holytime.backend.ant.Ant;
import com.example.holytime.backend.nest.Nest;

import java.time.LocalTime;
import java.util.Arrays;

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

    public double calculatePitScore(Ant ant, String weekDay, int timeLeft, boolean visitedPit, double currentLatitude, double currentLongitude, String currentHour) {
        double score = 0;
        if(visitedPit){
            return -100;
        }
        if(ant.isAccesible() && !this.getAccesible()){
            return -100;
        }
        if(!this.isOpen(weekDay, timeLeft ,currentHour)){
            return -100;
        }
        //the pit is available and open and can be visited
        //add preferences
        for (String preference : ant.getPreferences()) {
            if (Arrays.asList(this.getType()).contains(preference)) {
                score += 100;
            }else{
                score += 20;
            }
        }

        //add profile
        for (String profile : ant.getProfiles()) {
            if (Arrays.asList(this.getProfile()).contains(profile)) {
                score += 50;
            }
        }

        score = score + this.getFame()*10 + this.getImportance()*10;
        //add fame score and importance score

        double distance = DistanceCalculator.getDistance(currentLatitude, currentLongitude,this.getLatitude(), this.getLongitude() , 'K');

        score = score - distance*100;

        return score;
    }

    private boolean isOpen(String weekDay, int timeLeft , String currentHour) {
        //TODO: method that check if the pit is open
        boolean dayOpen = false;
        boolean isOpen = false;
        for(int i = 0; i< this.getDays().length; i++){
            if(this.getDays()[i].equals(weekDay)){
                dayOpen = true;
                if(this.getSchedule().equals("Guia")){
                    //TODO: check if the guide is available
                    System.out.println(" "+ this.getName() +" Necesita Guia");
                }else{
                    String hoursSchedule[] = this.getSchedule().split("/");
                    for(int j = 0; j< hoursSchedule.length; j++){
                        String hours [] = hoursSchedule[j].split("-");
                        if(  checkIfOpenByHour( hours[0], hours[1], currentHour) ){
                            isOpen = true;
                        }

                    }
                }


            }
        }

        return dayOpen && isOpen;

    }

    private boolean checkIfOpenByHour(String openTime, String CloseTime, String currentHour) {
        LocalTime timeCurrentHour = LocalTime.parse( currentHour );
        LocalTime t1 = LocalTime.parse( openTime );
        LocalTime t2 = LocalTime.parse( CloseTime );

        boolean result = false;
        if(timeCurrentHour.isAfter(t1) && timeCurrentHour.isBefore(t2)){
            result = true;
        }
        return result;
    }



}
