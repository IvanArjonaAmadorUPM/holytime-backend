package com.example.holytime.backend.matrix;

public class Matrix {
    private int time;
    private double distance;

    public Matrix() {
    }
    public Matrix(int time, double distance) {
        this.time = time;
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
