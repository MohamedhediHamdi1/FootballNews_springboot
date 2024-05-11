package com.FootballNews.FootballNews.Controllers;

import com.FootballNews.FootballNews.Entities.NewsEntity;
import com.FootballNews.FootballNews.Repositories.NewsRepository;
import com.FootballNews.FootballNews.Services.ImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageServices fileStorageService;
    @Autowired
    NewsRepository newsRepository;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            String fileName = fileStorageService.saveFile(file);
            return new ResponseEntity<String>(fileName, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") String id) {
        try {


            // Read the file from the directory
            Resource resource = fileStorageService.getFile(id);
            String contentType = "image/png";
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
