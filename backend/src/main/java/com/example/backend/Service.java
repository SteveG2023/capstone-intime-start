package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor



@org.springframework.stereotype.Service


public class Service {

    private final MongoUserDetailService service;
    private final com.example.backend.Repository repository;





    private final MongoUserRepo user;
    static WebClient webClient = WebClient.create("https://maps.googleapis.com/maps/api");

    String API_Key = "AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8";


    public ResponseDuration timeWithoutTraffic(String place_idH, String place_idW) {

        return Objects.requireNonNull(webClient.get()

                        // .uri("/directions/json?departure_time=now&destination=place_id:"+place_idW+"&origin=place_id:"+place_idH+"&key="+API_Key)
                        .uri("/directions/json?departure_time=now&destination=city=Hamburg&origin=city=Magdeburg&key=AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(ResponseDuration.class)
                        .block())
                .getBody();
    }


    // Diese Application funktioniert////////////////////////////////////////////

    public PlaceIdResponse Place_Id(String adresse, String street, String number) {


        return Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?address=" + adresse + "=street=" + street + "=number=" + number + "&key=" + API_Key)

                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(PlaceIdResponse.class)
                        .block())
                .getBody();
    }

    ResponseDuration duration(String citywork, String streetwork, String numberwork, String cityhome, String streethome, String numberhome) {
       // WebClient responseEntity =  webClient;

        return Objects.requireNonNull(webClient.get()
                .uri("/directions/json?departure_time=now&destination=city="+cityhome+"=street="+streethome+"number="+numberhome+"&origin=city=+"+citywork+"=street=+"+streetwork+"number="+numberwork+"&key=AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")
                .retrieve()
                .toEntity(ResponseDuration.class)
                .block())
                .getBody();




    }




}










