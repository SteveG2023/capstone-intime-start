package com.example.backend;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@Data
@NoArgsConstructor
public class Duration {

            @JsonProperty("text")
            private String text;

            @JsonProperty("value")
            private int value;


        }



