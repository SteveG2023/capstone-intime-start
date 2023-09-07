package com.example.backend;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.RouteMatcher;

import java.util.List;
@Data
@NoArgsConstructor
public class ResponseDuration {

    List<Route> routes;
}
