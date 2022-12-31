package com.example.holytime.backend.stop;

import com.example.holytime.backend.nest.Nest;

public class Stop extends Nest {
    public String startTime;
    public String finishTime;
    public Stop() {
        super();
    }
    public Stop(Nest nextStep, String startTime, String endTime) {
        super(nextStep.getName(), nextStep.getId(), nextStep.getProfile(), nextStep.getType(), nextStep.getLatitude(), nextStep.getLongitude(), nextStep.getVisitTime(), nextStep.getPrice(), nextStep.getDays(), nextStep.getSchedule(), nextStep.getAccesible());
        this.startTime = startTime;
        this.finishTime = endTime;
    }
}
