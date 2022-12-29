package com.example.holytime.backend.ecosystem;

import com.example.holytime.backend.ant.Ant;
import com.example.holytime.backend.event.Event;
import com.example.holytime.backend.firebase.FirebaseService;
import com.example.holytime.backend.google.GoogleService;
import com.example.holytime.backend.matrix.Matrix;
import com.example.holytime.backend.nest.Nest;
import com.example.holytime.backend.pit.Pit;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.io.IOException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Ecosystem {

    private int timeLeft;
    private Date currentDate;

    private String currentHour;
    private double currentLatitude;
    private double currentLongitude;
    int currentId;
    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    private String weekDay;
    private ArrayList<Pit> PitList = new ArrayList<Pit>();
    private HashMap<Integer, Event> EventsList = new HashMap<Integer, Event>();
    private ArrayList<Nest> FoodList = new ArrayList<Nest>();
    public final int pheromonesValue = 10;
    private boolean[] visitedPits;

    private boolean isEventSelected;
    private boolean isFoodSelected;
    private int stopsId = 0;
    private boolean isAntVisitedFood = false;
    Map<String, Object> pheromonesGraph = new HashMap<String, Object>();

    public Ecosystem() {

    }

    public void initPitPheromonesGraph() {
        ////Crear grafo de feromonas
        FirebaseService firebaseService = new FirebaseService();
        ObjectMapper oMapper = new ObjectMapper();

        try {
                //get class type of object
                this.pheromonesGraph = firebaseService.getPheromones();
                for (int i = 0; i < this.PitList.size(); i++) {
                    int auxiliarArray[] = oMapper.convertValue(this.pheromonesGraph.get(Integer.toString(i)), int[].class);
                    this.pheromonesGraph.put(Integer.toString(i), auxiliarArray);
                }
                System.out.println("Estado inicial del grafo de feromonas");
                this.printPheromonesGraph();


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void printPheromonesGraph() {
        System.out.println("Grafo de feromonas");
        for (String clave : this.pheromonesGraph.keySet()) {
            System.out.println("Clave: " + clave);
            System.out.println(this.pheromonesGraph.get(clave));
        }
    }

    void changePheromonesRouteValue(String pit1, int pit2) {
        ArrayList aux = (ArrayList) this.pheromonesGraph.get(pit1);
        aux.set(pit2, (long) aux.get(pit2) + this.pheromonesValue);
        this.pheromonesGraph.put(pit1, aux);
    }

    long getPheromonesRouteValue(String pit1, int pit2) {
        ArrayList aux = (ArrayList) this.pheromonesGraph.get(pit1);
        return (long) aux.get(pit2);
    }


    public void initPitList() {
        Pit pit;
        pit = new Pit("Capilla del Oidor", 1, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Museo"}, 40.4814468, -3.3636800, 30, 0.0f, new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"}, "10:00-14:00/16:00-19:00", true, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Centro de Interpretación Alcalá Medieval", 2, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Museo"}, 40.4812400, -3.3711437, 15, 0.0f, new String[]{"Jueves", "Viernes", "Sabado", "Domingo"}, "11:00-14:00", true, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Murallas Medievales", 3, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Monumento"}, 40.4831888, -3.3721432, 15, 0.0f, new String[]{"Jueves", "Viernes", "Sabado", "Domingo"}, "11:00-14:00", true, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Torre de la Antigua Iglesia de Santa María", 4, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Monumento"}, 40.4817368, -3.3638800, 40, 0.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "11:00-14:00", false, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Ciudad Romana de Complutum", 5, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Arqueologia"}, 40.4743104, -3.3862696, 80, 0.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "10:00-14:00/16:00-18:00", true, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Yacimiento Arqueológico Casa de Hippolytus", 6, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Arqueologia"}, 40.4767920, -3.3907310, 40, 0.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "10:00-14:00/16:00-19:00", true, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Corral de Comedias", 7, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Edificio"}, 40.4824717, -3.3644645, 35, 3.0f, new String[]{"Lunes", "Martes", "Miercoles", "Sabado",}, "Guia", false, 0, 0, new String[]{"11:30", "12:30", "13:30", "17:00"});
        this.PitList.add(pit);
        pit = new Pit("Universidad de Alcalá", 8, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Edificio"}, 40.4829939, -3.3631127, 60, 6.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "Guia", true, 0, 0, new String[]{"10:00", "13:00", "16:00", "18:00"});
        this.PitList.add(pit);
        pit = new Pit("Palacio Laredo. Museo Cisneriano", 9, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Museo"}, 40.4880255, -3.3639165, 30, 1.0f, new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "10:30-13:30/16:30-18:30", false, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Museo Casa Natal Cervantes", 10, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Museo"}, 40.4824514, -3.3671649, 40, 0.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "10:00-18:00", true, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Catedral Magistral", 11, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Religioso"}, 40.4809402, -3.3691787, 20, 2.0f, new String[]{"Lunes", "Martes", "Miercoles", "Sabado", "Viernes"}, "10:30-14:00/16:00-18:00", true, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Torre de la Catedral Magistral", 12, new String[]{"Familiar", "Amigos", "Infantil", "Solo", "Joven"}, new String[]{"Torre"}, 40.4809402, -3.3691787, 20, 2.0f, new String[]{"Lunes", "Martes", "Miercoles", "Sabado", "Viernes"}, "10:30-14:00/16:00-18:00", false, 0, 0, new String[]{});
        this.PitList.add(pit);
        pit = new Pit("Monasterio de San Bernardo", 13, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Religioso"}, 40.4832498, -3.3697772, 30, 3.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "Guia", true, 0, 0, new String[]{"12:15", "13:15"});
        this.PitList.add(pit);
        pit = new Pit("Monasterio de San Juan de la Penitencia", 14, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Religioso"}, 40.4832498, -3.3697772, 30, 0.0f, new String[]{""}, "", false, 0, 0, new String[]{""});
        this.PitList.add(pit);
        pit = new Pit("Antiguo Hospital de Nuestra Señora de la Misericordia", 15, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Edificio"}, 40.4824021, -3.3668985, 30, 3.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"}, "Guia", true, 0, 0, new String[]{"13:30", "16:00"});
        this.PitList.add(pit);
        pit = new Pit("Museo Arqueológico Regional", 16, new String[]{"Familiar", "Amigos", "Mayores", "Infantil", "Solo", "Joven"}, new String[]{"Museo"}, 40.4829347, -3.3692261, 40, 0.0f, new String[]{"Martes", "Miercoles", "Jueves", "Viernes", "Sabado"}, "11:00-19:00", true, 0, 0, new String[]{"11:00", "12:15"});
        this.PitList.add(pit);
    }

    public ArrayList<Pit> getPitList() {
        return PitList;
    }

    public HashMap<Integer, Event> getEventsList() {
        return EventsList;
    }

    public ArrayList<Nest> getFoodList() {
        return FoodList;
    }

    public void initEventList() {
        FirebaseService firebaseService = new FirebaseService();
        try {
            this.EventsList = firebaseService.getEvents();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void initFoodList() {
    }

    public void initEcosystemDate(Ant ant) throws ParseException {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek day = currentDate.getDayOfWeek();

        this.timeLeft = ant.getTime() * 60;
        this.weekDay = this.getWeekDay(day.name());
    }

    public String getWeekDay(String day) {
        if (day.equals("MONDAY"))
            return "Lunes";
        if (day.equals("TUESDAY"))
            return "Martes";
        if (day.equals("WEDNESDAY"))
            return "Miercoles";
        if (day.equals("THURSDAY"))
            return "Jueves";
        if (day.equals("FRIDAY"))
            return "Viernes";
        if (day.equals("SATURDAY"))
            return "Sabado";

        return "Domingo";
    }

    public void initLocalVariables(Ant ant) {
        if (this.visitedPits == null) this.visitedPits = new boolean[this.PitList.size()];

        for (int i = 0; i < this.PitList.size(); i++) {
            this.visitedPits[i] = false;
        }
        this.currentLatitude = ant.getLatitude();
        this.currentLongitude = ant.getLongitude();

        this.currentHour = ant.getHour();

        this.isEventSelected = ant.getEvents().length > 0;
        this.isFoodSelected = ant.getFood().length > 0;
    }

    public boolean continueRoute() {
        int timeMargin = 10;
        return this.timeLeft > timeMargin;
    }

    public Integer getStopId() {
        int solution = this.stopsId;
        this.stopsId++;
        return solution;
    }

    public Nest getStop(Ant ant) {
        //TODO GET FOOD
        if (this.isEventSelected) {
            //event = chooseEvent
            Nest event = this.chooseEvent(ant);
            if (event != null) {
                return event;
            } else {
            Pit pit = this.choosePit(ant);
            return pit;
            }
        }else {
            Pit pit = this.choosePit(ant);
            return pit;
        }
    }

    private Pit choosePit(Ant ant) {
        //return event if there is one, and return null if there is not
        double[] pitScore = new double[this.PitList.size()];
        for (int i = 0; i < this.PitList.size(); i++) {
            Pit pit = this.PitList.get(i);
            pitScore[i] = pit.calculatePitScore(ant, this.weekDay, this.timeLeft, this.visitedPits[i], this.currentLatitude, this.currentLongitude, this.currentHour);

            // add score of pheromones
            if(this.currentId!=-1){ // if current position is a pit
                pitScore[i] += this.getPheromonesRouteValue(String.valueOf(this.currentId-1), i);
            }
            System.out.println("Pit " + pit.getName() + " score: " + pitScore[i]);

        }
        //get max value from pit score
        double max = 0;
        int maxPitPosition = -1;
        for (int j = 0; j < pitScore.length; j++) {
            if (pitScore[j] > max) {
                max = pitScore[j];
                maxPitPosition = j;
            }
        }
        if(maxPitPosition == -1){
            return null;
        }else{ // update visited pit
            this.visitedPits[maxPitPosition] = true;
            return this.PitList.get(maxPitPosition);
        }
    }

    public Nest chooseEvent(Ant ant){
        //return event if there is one, and return null if there is not
        for (Map.Entry<Integer, Event> entry : this.EventsList.entrySet()) {
            Event event = entry.getValue();
            if(event.isDayOpen() && !event.visited){
                if(event.checkOpenHours(this.currentHour, ant)){
                    event.visited = true;
                    return event;
                }

            }

        }
        return null;
    }

    public void updateCurrentLocation(double latitude, double longitude) {
        this.currentLatitude = latitude;
        this.currentLongitude = longitude;
    }

    public void updateTimeLeft(int totalTimeSpent) {
        this.timeLeft -= totalTimeSpent;
    }

    public void updateCurrentHour(int totalTimeSpent) {
        int currentHour = Integer.parseInt(this.currentHour.split(":")[0]);
        int currentMinute = Integer.parseInt(this.currentHour.split(":")[1]);
        int newMinute = currentMinute + totalTimeSpent;
        int newHour = currentHour + newMinute / 60;
        newMinute = newMinute % 60;
        //check if new minute is 1 long
        if (newMinute < 10) {
            this.currentHour = newHour + ":0" + newMinute;
        } else {
            this.currentHour = newHour + ":" + newMinute;
        }
    }

    public String getCurrentHour() {
        return this.currentHour;
    }

    public String getTimeLeft() {
        return this.timeLeft + " min";
    }

    public Matrix getMovement(double currentLatitude, double currentLongitude, double nextLatitude, double nextLongitude) {
        GoogleService googleService = new GoogleService();
        try {
            String result = googleService.getMatrix(currentLatitude, currentLongitude, nextLatitude, nextLongitude);
            JSONParser jp = new JSONParser();
            JSONObject jo = (JSONObject) jp.parse(result);
            JSONArray ja = (JSONArray) jo.get("rows");
            jo = (JSONObject) ja.get(0);
            ja = (JSONArray) jo.get("elements");
            jo = (JSONObject) ja.get(0);
            JSONObject je = (JSONObject) jo.get("distance");
            JSONObject jf = (JSONObject) jo.get("duration");
            String distanceResponse = (String) je.get("text");
            String timeResponde = (String) jf.get("text");


            String time = timeResponde.split(" ")[0];
            String distance = distanceResponse.split(" ")[0];

            int timeInt = Integer.parseInt(time);
            if (distance.contains(",")) {
                distance = distance.replace(",", ".");
            }
            double distanceDouble = Double.parseDouble(distance);
            Matrix matrix = new Matrix(timeInt, distanceDouble);
            return matrix;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (net.minidev.json.parser.ParseException e) {
            throw new RuntimeException(e);
        }


    }
    public void updatePhremonesGraph(){

        this.evaporatePheromones();
        FirebaseService firebaseService = new FirebaseService();
        try {
            firebaseService.updatePheromonesMap(this.pheromonesGraph);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void evaporatePheromones() {
        // in pheromones map, if value > 2, value = value - 2
        for(int i = 0; i < this.pheromonesGraph.size(); i++){
            ArrayList aux = (ArrayList) this.pheromonesGraph.get(String.valueOf(i));
            for(int j = 0; j < aux.size(); j++){
                if((long) aux.get(j) > 2){
                    aux.set(j, (long) aux.get(j) - 2);}
            }
            this.pheromonesGraph.put(String.valueOf(i), aux);
        }
    }
}
