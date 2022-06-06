package com.example.individualproject.controller;

import com.example.individualproject.business.GenreService;
import com.example.individualproject.configuration.security.isauthenticated.IsAuthenticated;
import com.example.individualproject.dto.genre.GetGenreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class GenreController {

    private final GenreService genreService;

    //All
    @GetMapping("")
    public ResponseEntity<List<GetGenreDTO>> getAllGenres() {
        List<GetGenreDTO> genres = genreService.getAllGenres();

        if (genres.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(genres);
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @GetMapping("{name}")
    public ResponseEntity<GetGenreDTO> getGenreByName(@PathVariable("name") String name) {
        GetGenreDTO genres = genreService.getByName(name);

        if (genres != null) {
            return ResponseEntity.ok().body(genres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
