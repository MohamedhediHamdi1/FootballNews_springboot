package com.FootballNews.FootballNews.Services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Standing {

    @JsonProperty("standing_place")
    private int standing_place;

    @JsonProperty("standing_team")
    private String standing_team;

    @JsonProperty("standing_P")
    private int standing_P;

    @JsonProperty("standing_W")
    private int standing_W;

    @JsonProperty("standing_D")
    private int standing_D;

    @JsonProperty("standing_L")
    private int standing_L;

    @JsonProperty("standing_F")
    private int standing_F;
    @JsonProperty("standing_A")
    private int standing_A;
    @JsonProperty("standing_GD")
    private int standing_GD;
    @JsonProperty("standing_PTS")
    private int standing_PTS;

    @JsonProperty("team_logo")
    private String team_logo;

    public String getStanding_team() {
        return standing_team;
    }
}
