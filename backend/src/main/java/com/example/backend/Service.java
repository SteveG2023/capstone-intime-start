package com.example.backend;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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





    public PlaceIdResponse timeWithoutTraffic(String place_idH, String place_idW) {

        return Objects.requireNonNull(webClient.get()
                        .uri("/directions/json?destination=place_id=:"+place_idH+"&origin=place_id="+place_idW+"&key="+API_Key)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(PlaceIdResponse.class)
                        .block())
                .getBody();
    }



    // Diese Application funktioniert////////////////////////////////////////////

    public PlaceIdResponse Place_Id(String adresse , String street , String number) {


        return Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?address=" + adresse + "=street=" + street + "=number=" + number + "&key=" + API_Key)
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



    */




    }




