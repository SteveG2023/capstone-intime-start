package com.example.backend;

import lombok.Data;

@Data
public class Leg<S> {

    private Distance distance;
    private Duration duration;
    private Duration duration_in_traffic;



    public static class Distance {
        private String text;



    }

    public static class Duration {
        private String text;



    }
}