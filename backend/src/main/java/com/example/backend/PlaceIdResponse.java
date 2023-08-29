package com.example.backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaceIdResponse {
    @JsonProperty("results")
    private Result[] results;

    @JsonProperty("status")
    private String status;




    public String getStatus() {
        return status;
    }

    private static class Result {
        @JsonProperty("place_id")
        private String placeId;


    }
}