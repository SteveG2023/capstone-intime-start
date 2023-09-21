package com.example.backend;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
public class Traffic {
    private Duration duration;
    private Duration_in_Traffic duration_in_traffic;
}
