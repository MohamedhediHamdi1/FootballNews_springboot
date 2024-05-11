package com.FootballNews.FootballNews.Controllers;

import com.FootballNews.FootballNews.Services.ApiService;
import com.FootballNews.FootballNews.Services.MatchInfo;
import com.FootballNews.FootballNews.Services.Results;
import com.FootballNews.FootballNews.Services.Standing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/all")
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("/standings")
    public ResponseEntity<List<Standing>> standigs(){
        try {
            List<Standing> list = apiService.getStandings();
            return new ResponseEntity<> (list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/results")
    public ResponseEntity<List<Results>> results(){
        try {
            List<Results> list = apiService.getResults();
            return new ResponseEntity<> (list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/fixtures")
    public ResponseEntity<List<MatchInfo>> fixtures(){
        try {
            List<MatchInfo> list = apiService.getMatches();
            list.sort(Comparator.comparing(MatchInfo::getEvent_date));
            return new ResponseEntity<> (list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
