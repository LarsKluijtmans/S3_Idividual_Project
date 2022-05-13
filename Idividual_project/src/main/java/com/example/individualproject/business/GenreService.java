package com.example.individualproject.business;

import com.example.individualproject.dto.genre.GetGenreDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GenreService {
    List<GetGenreDTO> getAllGenres();
}
