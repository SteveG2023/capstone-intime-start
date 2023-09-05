package com.example.backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RouteDuration {

    @JsonProperty("text")
    private String text;


}
