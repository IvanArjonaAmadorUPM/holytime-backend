package com.example.holytime.backend.ecosystem;

import com.example.holytime.backend.ant.Ant;
import com.example.holytime.backend.nest.Nest;
import com.example.holytime.backend.pit.Pit;
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

            HashMap<Integer, Nest> solution = new HashMap<Integer, Nest>();

            while(this.ecosystem.continueRoute()){
                int iteracion = -1;
                iteracion++;
                // choose new stop in route
                Nest nextStep = this.ecosystem.getStop(ant);
                if(nextStep==null){
                    break;
                }
                //TODO add movement to route

                //add stop to route
                solution.put(this.ecosystem.getStopId(), nextStep);

                // TODO need to add time of movement
                int totalTimeSpent = nextStep.getVisitTime();
                this.ecosystem.updateCurrentHour(totalTimeSpent);
                this.ecosystem.updateTimeLeft(totalTimeSpent);
                this.ecosystem.updateCurrentLocation(nextStep.getLatitude(), nextStep.getLongitude());

                System.out.println("Iteracion : " + iteracion);
                System.out.println("Hora actual : " + this.ecosystem.getCurrentHour());
                System.out.println("Tiempo restante : " + this.ecosystem.getTimeLeft());
                System.out.println("PIT " + nextStep.getName() + " visitado");
                System.out.println("-----------------------------------------------");


            }
            System.out.println("///////////////////////////////////////////////");
            System.out.println("La ruta es");
            // show route
            System.out.println("longitud de la visita: " + solution.size());

            for (int i = 0; i < solution.size(); i++) {
                Nest nest = solution.get(i);
                System.out.println("Iteración: "+ i + "elegido: " + nest.getName());

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
