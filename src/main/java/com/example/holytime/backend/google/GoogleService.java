package com.example.holytime.backend.google;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class GoogleService {

    public String getMatrix(double originLat, double originLong, double destinationLat, double destinationLong) throws IOException {
        String targetURL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + originLat + "," + originLong + "&destinations=" + destinationLat + "," + destinationLong + "&mode=walking&key=" + this.readFile(StandardCharsets.UTF_8);
        var request = HttpRequest.newBuilder().GET().uri(URI.create(targetURL)).build();
        var client = HttpClient.newBuilder().build();
        String response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
    private String readFile(Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/envKeys"));
        return new String(encoded, encoding);
    }
}






