package com.dhan.nasaasteroids.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class NeoResponse {
    @JsonProperty("links")
    private Links links;

    @JsonProperty("element_count")
    private int element_count;

    @JsonProperty("near_earth_objects")
    private Map<String, List<NeoObject>> nearEarthObjects;
}
