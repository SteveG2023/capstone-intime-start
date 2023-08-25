package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
@RequiredArgsConstructor


@org.springframework.stereotype.Service


public class Service {



    private final Useranfragen useranfragen;

    private static WebClient webClient = WebClient.create("https://maps.googleapis.com/maps/api");


    public static String getDataHomeAddressGeocode(String adresse, String street, int number) {

        return Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?/address="+adresse,"=street="+street,"=number="+number,"&key=AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(String.class)
                        .block())
                .getBody();
    }


    public Useranfragen timeWithoutTraffic(String place_idA, String place_idB) {

        return Objects.requireNonNull(webClient.get()
                        .uri("/directions/json?destination=place_id:"+place_idA,"&origin=place_id:"+place_idB,"&key=")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(Useranfragen.class)
                        .block())
                .getBody();
    }
    public Useranfragen timeWithTraffic(String place_idA, String place_idB) {

        return Objects.requireNonNull(webClient.get()
                        .uri("/directions/json?destination=place_id:"+place_idA,"&origin=place_id:"+place_idB,"&key=")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(Useranfragen.class)
                        .block())
                .getBody();
    }



}