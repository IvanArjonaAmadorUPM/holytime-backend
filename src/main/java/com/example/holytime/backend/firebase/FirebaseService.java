package com.example.holytime.backend.firebase;

import com.example.holytime.backend.event.Event;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public HashMap<Integer, Event> getEvents() throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("eventos").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.getData());
        }
        //convert documents to list of events
        HashMap<Integer,Event> events = new HashMap<>();
        int id = 1;
        for (DocumentSnapshot document : documents) {
            String initHour = document.getString("horaInicio");
            String finishHour = document.getString("horaFinal");
            int duration = 0;
            if(initHour.equals("Todo el día")){
                duration = 2;
            }else {
                duration =  Integer.parseInt(finishHour.split(":")[0]) - Integer.parseInt( initHour.split(":")[0]);
            }
            float price = 0;

            if(document.getString("precio").equals("Gratis")){
                price = 0;
            }else {
                price = Float.parseFloat(document.getString("precio"));
            }
            ArrayList<Integer> startDay = (ArrayList<Integer>) document.get("fechaInicio");
            ArrayList<Integer> endDay = (ArrayList<Integer>) document.get("fechaFin");

            Event event = new Event(
                    id,
                    document.getString("nombre"),
                    document.getId(),
                    document.get("perfil").toString().replace("[","").replace("]","").split(","),
                    document.getString("tipo"),
                    document.getGeoPoint("geolocalización").getLatitude(),
                    document.getGeoPoint("geolocalización").getLongitude(),
                    duration*60,
                    price,
                    // schedule(always null)
                    null,
                    document.getBoolean("accesible"),
                    document.getString("horaInicio"),
                    document.getString("horaFinal"),
                    startDay,
                    endDay
            );
            events.put(event.getId(),event);
            id++;
        }
        return events;
    }

    public Map<String, Object> getPheromones() throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = dbFirestore.collection("pheromones").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            return document.getData();
        }
        return null;
    }
    //
    public void updatePheromonesMap( Map map) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFirestore.collection("pheromones").document("pheromones").set(map);
    }


    public void saveRoute(JSONObject jsonObject, String userEmail) throws ExecutionException, InterruptedException{
        this.checkUser(userEmail);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String currentTime = java.time.LocalDate.now().toString() + "_" + java.time.LocalTime.now().getHour() + ":" + java.time.LocalTime.now().getMinute();
        ApiFuture<WriteResult> future = dbFirestore.collection("userData").document(userEmail).collection("routes").document(currentTime).create(jsonObject);
    }

    private void checkUser(String userEmail) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("userData").get();
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            if(document.getId().equals(userEmail)){
                return;
            }
        }
        //if user not exists, create it
        Map<String, Object> user = new HashMap<>();
        user.put("email", userEmail);
        ApiFuture<WriteResult> future2 = dbFirestore.collection("userData").document(userEmail).set(user);
    }
}
