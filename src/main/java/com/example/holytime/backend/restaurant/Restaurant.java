package com.example.holytime.backend.restaurant;

import com.example.holytime.backend.nest.Nest;

import java.time.LocalTime;

public class Restaurant extends Nest {

    public Restaurant() {
        super();
    }
    public Restaurant(String name, int id, String[] profile, String[] type, double latitude, double longitude, int visitTime, float price, String[] days, String schedule, boolean accesible) {
        super(name, id, profile, type, latitude, longitude, visitTime, price, days, schedule, accesible);
    }

    public boolean isOpen(String weekDay , String currentHour) {
        //method that check if the pit is open
        boolean dayOpen = false;
        boolean isOpen = false;
        for(int i = 0; i< this.getDays().length; i++){
            if(this.getDays()[i].equals(weekDay)){
                dayOpen = true;
                    String hoursSchedule[] = this.getSchedule().split("/");
                    for(int j = 0; j< hoursSchedule.length; j++){
                        String hours [] = hoursSchedule[j].split("-");
                        if(checkIfOpenByHour( hours[0], hours[1], currentHour) ){
                            isOpen = true;
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

        boolean result = timeCurrentHour.isAfter(t1) && timeCurrentHour.isBefore(t2);
        return result;
    }

    public boolean isInPreferences(String[] food) {
        //method that check if the pit is in preferences
        boolean isInPreferences = false;
        for(int i = 0; i< food.length; i++){
            for(int j = 0; j< this.getType().length; j++){
                if(food[i].equals(this.getType()[j])){
                    isInPreferences = true;
                }
            }
        }
        return isInPreferences;
    }
}
