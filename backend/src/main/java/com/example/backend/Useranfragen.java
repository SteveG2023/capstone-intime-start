package com.example.backend;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@AllArgsConstructor
@NoArgsConstructor
@Data


public class Useranfragen {


    private String place_idA;
    private String place_idB;



    private float duration;
    private float distance;

    private String adresse;
    private int    number;
    private String street;
    private String schluessel;




}
