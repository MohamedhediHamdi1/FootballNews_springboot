package com.FootballNews.FootballNews.Controllers;

import com.FootballNews.FootballNews.Entities.NewsEntity;
import com.FootballNews.FootballNews.Repositories.NewsRepository;
import com.FootballNews.FootballNews.Responses.NewsResponse;
import com.FootballNews.FootballNews.Services.ApiService;
import com.FootballNews.FootballNews.Services.Standing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ApiService apiService;

    @PostMapping
    public ResponseEntity createNews(@RequestBody NewsResponse request){
        if(newsRepository.findByTitle(request.getTitle()) !=null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST) ;
        }
        NewsEntity newEntity=new NewsEntity();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(request,newEntity);
        Date date=new Date();
        newEntity.setDate(date);
        newsRepository.save(newEntity);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @GetMapping("/all/{page}")
    public ResponseEntity<Map<String,Object>> getAll(@PathVariable int page){
        List<NewsResponse> list=new ArrayList<>();
        Map<String,Object> response=new HashMap<>();
        Pageable pageable= PageRequest.of(page, 10);
        Page<NewsEntity> pages=newsRepository.findpage(pageable);
        for(NewsEntity entity:pages){
            entity.setDescription("");
            ModelMapper modelMapper=new ModelMapper();
            list.add(modelMapper.map(entity,NewsResponse.class));
        }
        response.put("list",list);
        response.put("size",pages.getTotalElements());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<NewsResponse> getOne(@PathVariable String title){
        try {
            NewsEntity newsEntity = newsRepository.findByTitle(title);
            ModelMapper modelMapper=new ModelMapper();
            NewsResponse response=modelMapper.map(newsEntity,NewsResponse.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/delete/{title}")
    public ResponseEntity delete(@PathVariable String title){
        try {
            NewsEntity newsEntity = newsRepository.findByTitle(title);
            newsRepository.delete(newsEntity);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }





}
