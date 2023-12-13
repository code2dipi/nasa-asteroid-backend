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

public class CloseApproachData {
    @JsonProperty("close_approach_date")
    private String closeApproachDate;

    @JsonProperty("close_approach_date_full")
    private String closeApproachDateFull;

    @JsonProperty("epoch_date_close_approach")
    private long epochDateCloseApproach;

    @JsonProperty("relative_velocity")
    private RelativeVelocity relativeVelocity;

    @JsonProperty("miss_distance")
    private MissDistance missDistance;

    @JsonProperty("orbiting_body")
    private String orbitingBody;
}