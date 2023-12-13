package com.dhan.nasaasteroids.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class MissDistance {
    @JsonProperty("astronomical")
    private String astronomical;

    @JsonProperty("lunar")
    private String lunar;

    @JsonProperty("kilometers")
    private String kilometers;

    @JsonProperty("miles")
    private String miles;
}
