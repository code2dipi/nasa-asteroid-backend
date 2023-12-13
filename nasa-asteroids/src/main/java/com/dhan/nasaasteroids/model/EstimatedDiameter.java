package com.dhan.nasaasteroids.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class EstimatedDiameter {
    @JsonProperty("kilometers")
    private DiameterUnit kilometers;

    @JsonProperty("meters")
    private DiameterUnit meters;

    @JsonProperty("miles")
    private DiameterUnit miles;

    @JsonProperty("feet")
    private DiameterUnit feet;

    @Data
    public static class DiameterUnit {
        @JsonProperty("estimated_diameter_min")
        private double min;

        @JsonProperty("estimated_diameter_max")
        private double max;
    }
}
