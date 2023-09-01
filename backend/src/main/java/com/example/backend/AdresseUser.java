package com.example.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AdresseUser {


    String wohnadressestadt;
    String wohnadressestrasse;
    String wohnadressenummer;
    String place_idHome;

    String arbeitsadressestadt;

    public AdresseUser(String wohnadressestadt, String wohnadressestrasse, String wohnadressenummer) {
        this.wohnadressestadt = wohnadressestadt;
        this.wohnadressestrasse = wohnadressestrasse;
        this.wohnadressenummer = wohnadressenummer;
    }

    String arbeitsadressestrasse;
    String arbeitsadressenummer;
    String place_idWork;



}

