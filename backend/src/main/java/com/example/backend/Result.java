package com.example.backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Result {
    @JsonProperty("place_id")
    private String placeId;
}