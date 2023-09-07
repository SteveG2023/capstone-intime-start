package com.example.backend;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class RouteInformation {
    private List<Leg>  legs;
}
