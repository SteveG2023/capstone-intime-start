package com.example.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data

@NoArgsConstructor


public class Route {
    //private List<RouteInformation> routeInformations;
    private List<Leg>  legs;
}










