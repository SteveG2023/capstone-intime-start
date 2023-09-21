package com.example.backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Duration_in_Traffic {




        @JsonProperty("text")
        private String text;

        @JsonProperty("value")
        private int value;


    }

