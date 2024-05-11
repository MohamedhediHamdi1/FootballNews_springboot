package com.FootballNews.FootballNews.Services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    private String apiKey="8a5d85c4943ca5f133a32634e757b3156a3ba6129413b805bdc5b87d501caf7a";
    List<MatchInfo> matches = new ArrayList<>();
    List<Standing> standings = new ArrayList<>();
    List<Results> results = new ArrayList<>();

    public List<Standing> getStandings() {
        return standings;
    }

    public List<MatchInfo> getMatches() {
        return matches;
    }

    public List<Results> getResults() {
        return results;
    }

    public void getStanding() throws JsonProcessingException {
        String url = "https://apiv2.allsportsapi.com/football/?&met=Standings&leagueId=302&APIkey=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        // Extract and process the response as needed
        String responseBody = responseEntity.getBody();
        System.out.println("getStanding : "+responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        standings=new ArrayList<>();
        for (JsonNode matchNode : rootNode.path("result").path("total")) {
            Standing stand = objectMapper.treeToValue(matchNode, Standing.class);
            standings.add(stand);
        }

    }

    public void getFixtures() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate fromDate = LocalDate.now(); // Start from the current date
        matches=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String fromTime = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate toDate = fromDate.plusDays(14);
            String toTime = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String url = "https://apiv2.allsportsapi.com/football/?met=Fixtures&APIkey=" + apiKey +
                    "&from=" + fromTime + "&to=" + toTime + "&teamId=76&timezone=GMT";

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String responseBody = responseEntity.getBody();
            System.out.println("getFixtures : "+responseBody);
            MatchInfo(responseBody);
            fromDate = toDate.plusDays(1); // Move to the next day
        }
    }


    public void getResult() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate fromDate = LocalDate.now();
        results=new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String fromTime = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate toDate = fromDate.minusDays(14);
            String toTime = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String url = "https://apiv2.allsportsapi.com/football/?met=Fixtures&APIkey=" + apiKey +
                    "&from=" + toTime + "&to=" + fromTime + "&teamId=76&timezone=GMT";

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String responseBody = responseEntity.getBody();
            System.out.println("getResult : "+responseBody);
            ResultsInfo(responseBody);
            fromDate = toDate.minusDays(1); // Move to the next day
        }
    }

    public List<Results> ResultsInfo(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        for (JsonNode matchNode : rootNode.path("result")) {
            Results result = objectMapper.treeToValue(matchNode, Results.class);
            results.add(result);
        }
        for(Results result : results){
            if(result.getLeague_name().contains("- Group Stage")){
                result.setLeague_name(result.getLeague_name().replace("- Group Stage",""));
                result.setLeague_round(" ");
            }
        }
        return results;
    }

    public List<MatchInfo> MatchInfo(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        for (JsonNode matchNode : rootNode.path("result")) {
            MatchInfo match = objectMapper.treeToValue(matchNode, MatchInfo.class);
            matches.add(match);
        }
        for(MatchInfo result : matches){
            if(result.getLeagueName().contains("- Group Stage")){
                result.setLeagueName(result.getLeagueName().replace("- Group Stage",""));
                result.setLeagueRound(" ");
            }
            if(result.getLeagueName().contains("finals")){
                result.setLeagueRound(" ");
            }
        }
        return matches;
    }


    @Scheduled(fixedRate = 3600000)
    public void run() throws Exception {
        System.out.println("Starting ...");
        getFixtures();
        getStanding();
        getResult();
    }
}
