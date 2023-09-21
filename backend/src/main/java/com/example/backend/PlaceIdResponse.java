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
        return getPlaceId();
    }

    public String getPlaceId() {
        if (results != null && results.length > 0) {

            return results[0].placeId;
        }
        return null;
    }
    @Data
    private static class Result {
        @JsonProperty("place_id")
        private String placeId;
    }
}