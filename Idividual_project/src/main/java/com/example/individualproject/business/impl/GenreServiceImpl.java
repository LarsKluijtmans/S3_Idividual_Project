package com.example.individualproject.business.impl;

import com.example.individualproject.business.GenreService;
import com.example.individualproject.dto.genre.GetGenreDTO;
import com.example.individualproject.repository.GenreRepository;
import com.example.individualproject.repository.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;

   @Override
    public List<GetGenreDTO> getAllGenres() {
      List<GetGenreDTO> result = new ArrayList<>();

      GetGenreDTO genre;

       for (Genre g: genreRepository.findAll()) {
           genre = GetGenreDTO.builder()
                   .id(g.getId())
                   .genre(g.getGenre())
                   .build();
           result.add(genre);
       }
       return result;
    }
}
