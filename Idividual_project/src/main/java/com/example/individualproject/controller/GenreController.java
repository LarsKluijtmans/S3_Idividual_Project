package com.example.individualproject.controller;

import com.example.individualproject.business.GenreService;
import com.example.individualproject.dto.genre.GetGenreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class GenreController {

    private final GenreService genreService;

    //All
    @GetMapping("getAll")
    public ResponseEntity<List<GetGenreDTO>> getAllProducts() {
        List<GetGenreDTO> genres = genreService.getAllGenres();

        if(genres.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(genres);
        }
    }

}
