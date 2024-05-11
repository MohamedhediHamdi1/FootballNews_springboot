package com.FootballNews.FootballNews.Services;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfo {

    @JsonProperty("event_date")
    private String event_date;

    @JsonProperty("event_time")
    private String event_time;
    @JsonProperty("event_home_team")
    private String homeTeam;

    @JsonProperty("event_away_team")
    private String awayTeam;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("league_round")
    private String leagueRound;

    @JsonProperty("event_stadium")
    private String stadium;

    @JsonProperty("home_team_logo")
    private String homeTeamLogo;

    @JsonProperty("away_team_logo")
    private String awayTeamLogo;

    @JsonProperty("league_logo")
    private String leagueLogo;

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueRound() {
        return leagueRound;
    }

    public String getStadium() {
        return stadium;
    }

    public String getHomeTeamLogo() {
        return homeTeamLogo;
    }

    public String getAwayTeamLogo() {
        return awayTeamLogo;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setLeagueRound(String leagueRound) {
        this.leagueRound = leagueRound;
    }

    // Add getters and setters


}
