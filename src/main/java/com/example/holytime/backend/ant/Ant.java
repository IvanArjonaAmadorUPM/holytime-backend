package com.example.holytime.backend.ant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
