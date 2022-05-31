package com.example.individualproject.business;

import com.example.individualproject.dto.genre.GetGenreDTO;

import java.util.List;


public interface GenreService {
    List<GetGenreDTO> getAllGenres();

    GetGenreDTO getByName(String name);
}
