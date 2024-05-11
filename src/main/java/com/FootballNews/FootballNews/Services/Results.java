package com.FootballNews.FootballNews.Services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    @JsonProperty("event_date")
    private String event_date;

    @JsonProperty("event_time")
    private String event_time;

    @JsonProperty("event_home_team")
    private String event_home_team;

    @JsonProperty("event_away_team")
    private String event_away_team;

    @JsonProperty("event_final_result")
    private String event_final_result;

    @JsonProperty("league_name")
    private String league_name;

    @JsonProperty("league_round")
    private String league_round;

    @JsonProperty("event_stadium")
    private String event_stadium;

    @JsonProperty("event_referee")
    private String event_referee;

    @JsonProperty("home_team_logo")
    private String home_team_logo;

    @JsonProperty("away_team_logo")
    private String away_team_logo;

    @JsonProperty("league_logo")
    private String league_logo;


    public String getEvent_home_team() {
        return event_home_team;
    }

    public String getEvent_away_team() {
        return event_away_team;
    }

    public String getLeague_name() {
        return league_name;
    }

    public String getLeague_round() {
        return league_round;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public void setLeague_round(String league_round) {
        this.league_round = league_round;
    }
}
