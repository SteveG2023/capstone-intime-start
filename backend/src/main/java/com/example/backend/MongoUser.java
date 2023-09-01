package com.example.backend;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    String TimeStart;

    String place_id;

    String fortbewegung;

}