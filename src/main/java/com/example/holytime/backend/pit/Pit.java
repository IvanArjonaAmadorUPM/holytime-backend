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

    final int unablePit = -10000;
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
            return unablePit;
        }
        if(ant.isAccesible() && !this.getAccesible()){
            return unablePit;
        }
        if(!this.isOpen(weekDay, timeLeft ,currentHour)){
            return unablePit;
        }
         //the pit is available and open and can be visited

            // If the pit needs guide, it will be considered if it is near the hour of the guide route. This is checked in the method isOpen.
            // that means that at this point, if the pit needs guide, it will be open. There is a margin of 10 minutes
        if(this.getSchedule().equals("Guia")){
            score+= 45;
        }
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
            //add fame score and importance score
        score = score + this.getFame()*10 + this.getImportance()*10;

            //subtract distance
        double distance = DistanceCalculator.getDistance(currentLatitude, currentLongitude,this.getLatitude(), this.getLongitude() , 'K');
        score = score - distance*100;

        return score;
    }

    private boolean isOpen(String weekDay, int timeLeft , String currentHour) {
        //method that check if the pit is open
        boolean dayOpen = false;
        boolean isOpen = false;
        for(int i = 0; i< this.getDays().length; i++){
            if(this.getDays()[i].equals(weekDay)){
                dayOpen = true;
                if(this.getSchedule().equals("Guia")){
                    isOpen = this.isNearSpecialHour(currentHour);
                }else{
                    String hoursSchedule[] = this.getSchedule().split("/");
                    for(int j = 0; j< hoursSchedule.length; j++){
                        String hours [] = hoursSchedule[j].split("-");
                        if(checkIfOpenByHour( hours[0], hours[1], currentHour) ){
                            isOpen = true;
                        }
                    }
                }
            }
        }

        return dayOpen && isOpen;

    }

    private boolean isNearSpecialHour(String currentHour) {
        //method that check if the current hour is near a special hour
        boolean isNear = false;
        for(int i = 0; i< this.getSpecialHour().length; i++){
            String specialHour [] = this.getSpecialHour()[i].split(":");
            int pitSpecialHour = Integer.parseInt(specialHour[0]);
            int pitSpecialMinutes = Integer.parseInt(specialHour[1]);
            String currentHourArray [] = currentHour.split(":");
            int currentHourInt = Integer.parseInt(currentHourArray[0]);
            int currentMinutesInt = Integer.parseInt(currentHourArray[1]);

            int timeDifference = ((pitSpecialHour*60)+pitSpecialMinutes) - ((currentHourInt*60)+currentMinutesInt);
            if( timeDifference < 12 && timeDifference > 0) {
                isNear = true;
            }
        }
        return isNear;
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
