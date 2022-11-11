package com.example.holytime.backend.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FirebaseInitializer {
    public void init(){
        FileInputStream refreshToken = null;
        try {
            refreshToken = new FileInputStream("src/main/resources/firebaseHolytime.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://hollytime-40554.firebaseio.com")
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FirebaseApp.initializeApp(options);
    }
    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
}
