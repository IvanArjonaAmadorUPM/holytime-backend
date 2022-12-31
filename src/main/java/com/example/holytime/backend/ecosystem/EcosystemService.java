package com.example.holytime.backend.ecosystem;

import com.example.holytime.backend.ant.Ant;
import com.example.holytime.backend.event.Event;
import com.example.holytime.backend.matrix.Matrix;
import com.example.holytime.backend.nest.Nest;
import com.example.holytime.backend.pit.Pit;
import com.example.holytime.backend.stop.Stop;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EcosystemService {
        Ecosystem ecosystem;
        public EcosystemService() {
            this.ecosystem = new Ecosystem();
        }
        public String createNewRoute(Ant ant) {

            System.out.println("La peticion es");
            System.out.println(ant.toString());
            initEcosystem(ant);

            HashMap<Integer, Nest> stops = new HashMap<Integer, Nest>();
            HashMap<Integer, Matrix> movements = new HashMap<Integer, Matrix>();

            //init ecosystem steps positions
            this.ecosystem.currentId = -1;
            int lastPosition = -1;
            while(this.ecosystem.continueRoute()){
                // choose new stop in route
                Nest nextStep = this.ecosystem.getStop(ant);
                if(nextStep==null){
                    break;
                }
                //getPheromonesRouteValue
                if(nextStep instanceof Pit){
                    lastPosition = this.ecosystem.currentId;
                    this.ecosystem.currentId = nextStep.getId();
                    if(lastPosition!=-1 && this.ecosystem.currentId!=-1){
                        this.ecosystem.changePheromonesRouteValue(String.valueOf(lastPosition-1 ),this.ecosystem.currentId -1);
                    }
                }else{
                    this.ecosystem.currentId = -1;
                    lastPosition = -1;
                }
                //update  id
                int id = this.ecosystem.getStopId();
                //add movement to route
                Matrix matrix = ecosystem.getMovement(this.ecosystem.getCurrentLatitude(), this.ecosystem.getCurrentLongitude(), nextStep.getLatitude(), nextStep.getLongitude());


                //  add time of movement and update current values

                int totalTimeSpent = nextStep.getVisitTime() + matrix.getTime();
                if(nextStep instanceof Event && !((Event) nextStep).getStarHour().equals("Todo el día")) {
                    totalTimeSpent = totalTimeSpent +  ((Event) nextStep).waitingTime;
                    if( ((Event) nextStep).waitingTime > matrix.getTime()){
                        totalTimeSpent = totalTimeSpent - matrix.getTime();
                    }
                }
                //Add to the String startTime whose format is HH:mm the value of matrix.getTime whose value is an int in minutes. The result must be a string with the format HH:mm
                String startTime = this.ecosystem.addTime(this.ecosystem.getCurrentHour(), matrix.getTime());

                this.ecosystem.updateCurrentHour(totalTimeSpent);
                this.ecosystem.updateTimeLeft(totalTimeSpent);
                this.ecosystem.updateCurrentLocation(nextStep.getLatitude(), nextStep.getLongitude());

                String endTime = this.ecosystem.getCurrentHour();

                Stop stop = new Stop(nextStep, startTime, endTime);
                //add stop to route
                movements.put(id, matrix);
                stops.put(id, stop);

                System.out.println("Hora actual : " + this.ecosystem.getCurrentHour());
                System.out.println("Tiempo restante : " + this.ecosystem.getTimeLeft());
                System.out.println("PIT " + nextStep.getName() + " visitado");
                System.out.println("-----------------------------------------------");

            }
            System.out.println("///////////////////////////////////////////////");
            System.out.println("La ruta es");
            // show route
            System.out.println("longitud de la visita: " + stops.size());

            for (int i = 0; i < stops.size(); i++) {
                Nest nest = stops.get(i);
                Matrix matrix = movements.get(i);
                if(matrix!=null){
                    System.out.println("Movimiento: " + matrix.getTime() + " minutos" + " distancia: " + matrix.getDistance() + " KM");
                }
                System.out.println("Iteración: "+ (i+1) + " elegido: " + nest.getName());

            }
            //save pheromones in database
            this.ecosystem.updatePhremonesGraph();
            //create a JSON object with the stops and movements and ant
            Gson gson = new Gson();
            String stopsJson = gson.toJson(stops);
            String movementsJson = gson.toJson(movements);
            String antJson = gson.toJson(ant);
            String result = "{\"stops\":"+stopsJson+",\"movements\":"+movementsJson+",\"ant\":"+antJson+"}";

            Map<String, Object> data = new HashMap<>();
            data.put("route", result);
            JSONObject jsonObject = new JSONObject(data);

            //save route in database
            this.ecosystem.saveRoute(jsonObject, ant.getUserEmail());
            return result;



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
