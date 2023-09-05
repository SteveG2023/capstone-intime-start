package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Objects;

@RequiredArgsConstructor



@org.springframework.stereotype.Service


public class Service {

    private final MongoUserDetailService service;
    private final com.example.backend.Repository repository;

    private PlaceIdResponse placeIdResponse;

    private UserID userID;
    private Results results;
    private MongoUserDetailService mongoUserDetailService;

    private final MongoUserRepo user;
    static WebClient webClient = WebClient.create("https://maps.googleapis.com/maps/api");

    String API_Key = "AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8";





    public Leg timeWithoutTraffic(String place_idH, String place_idW) {

        return Objects.requireNonNull(webClient.get()
                        .uri("/directions/json?departure_time=now&destination=place_id:"+place_idW+"&origin=place_id:"+place_idH+"&key="+API_Key)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(Leg.class)
                        .block())
                .getBody();
    }







    // Diese Application funktioniert////////////////////////////////////////////

    public PlaceIdResponse Place_Id(String adresse , String street , String number) {


        return Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?address="+adresse+"=street="+street+"=number="+number+"&key="+API_Key)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(PlaceIdResponse.class)
                        .block())
                .getBody();
    }





/*
    public ApiService(WebClient.Builder webClientBuilder, Repository Repository) {
        this.webClient = webClientBuilder.baseUrl("DEINE_API_URL_HIER").build();
        this.Repository = Repository;
    }
/*
    public Mono<String> fetchAndCallApi(String address) {
            return Repository.findByAddress(address)
                    .map(placeData -> placeData.getPlaceId()) // Hier die place_id aus der MongoDB holen
                    .flatMap(placeId -> {
                        String apiUrl = "/geocode/json?place_id=" + placeId + "&key=DEIN_API_KEY_HIER";
                        return webClient.get()
                                .uri(apiUrl)
                                .retrieve()
                                .bodyToMono(String.class);
                    });


        }
            /*

    public PlaceIdResponse Test() {

        return   Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?address=Berlin=street=koenigsstrasse=number=1&key=AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(PlaceIdResponse.class)
                        .block())
                .getBody();





    private final WebClient webClient;

    public JsonSenderService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://routes.googleapis.com") // Ihre Ziel-URL hier eintragen
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("X-Goog-Api-Key", "AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")
                .defaultHeader("X-Goog-FieldMask", "routes.duration,routes.distanceMeters,routes.polyline.encodedPolyline")
                .build();
    }

    public Mono<Void> sendJsonRequest() {
        // Ihre JSON-Daten hier erstellen
        String jsonPayload = "{ \"origin\": {Magdeburg ... }, \"destination\": {Berlin ... }, ... }";

        return webClient.post()
                .uri("/directions/v2:computeRoutes")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsonPayload))
                .retrieve()
                .bodyToMono(Void.class);
    }


 */



    public Leg duration(String citywork , String streetwork , String numberwork, String cityhome , String streethome , String numberhome) {


        return Objects.requireNonNull(webClient.get()
                        .uri("/directions/json?departure_time=now&destination=place_id:ChIJAVkDPzdOqEcRcDteW0YgIQQ&origin=place_id:ChIJW-raVf_1pUcRYBAH-FlmIwQ&key=AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")

                        .retrieve()
                        .toEntity(Leg.class)
                        .block())
                .getBody();
    }









}




