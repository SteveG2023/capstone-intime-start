package com.example.backend;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Users")
@With
public class MongoUser {

    String id;
    String username;
    String password;
    String vorname;
    String nachname;
    String email;


    // Benutzung innerhalb der app;
    String wohnadressestadt;
    String wohnadressestrasse;
    String wohnadressenummer;
    String place_idHome;

    String arbeitsadressestadt;
    String arbeitsadressestrasse;
    String arbeitsadressenummer;
    String place_idWork;

    String routeTimewithoutTraffic;
    String routTimewithTraffic;

    private String startZeit;
    private String endZeit;
    private String vorbereitungsZeit;
    private String maximalweckZeit;



    int duration;




    String place_id;

    String distance;

    String fortbewegung;


    public MongoUser(String steve1, String steve, String gumpert) {
    }

    public MongoUser(String number, String johnDoe) {
    }


}
