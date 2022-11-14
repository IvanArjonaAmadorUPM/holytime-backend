package com.example.holytime.backend.ecosystem;

import com.example.holytime.backend.ant.Ant;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class EcosystemService {
        Ecosystem ecosystem;
        public EcosystemService() {
            this.ecosystem = new Ecosystem();
        }
        public void createNewRoute(Ant ant) {

            System.out.println("La peticion es");
            System.out.println(ant.toString());
            initEcosystem(ant);

            HashMap<Integer, Object> solution = new HashMap<Integer, Object>();

            while(this.ecosystem.continueRoute()){
                // choose new stop in route
                Object nextStep = this.ecosystem.getStop(ant);


                //add movement to route

                //add stop to route
                //solution.put(this.ecosystem.getStopId(), nextStep);

                // TODO update current hour, current location, time left, visitedPit, and other values
                break;
            }

    }

    public void initEcosystem(Ant ant) {

        System.out.println("El ecosistema se está inicializando");
        try {
            this.ecosystem.initEcosystemDate(ant);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.ecosystem.initPitPheromonesGraph();
        this.ecosystem.initPitList();
        if(ant.getEvents().length > 0) {
            System.out.println("Preparando eventos");
            this.ecosystem.initEventList();
        }
        if(ant.getFood().length > 0) {
            System.out.println("Preparando restauración");
            this.ecosystem.initFoodList();
        }
        this.ecosystem.initLocalVariables(ant);

    }

}
