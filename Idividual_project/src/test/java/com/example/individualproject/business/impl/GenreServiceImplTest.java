package com.example.individualproject.business.impl;

import com.example.individualproject.dto.genre.GetGenreDTO;
import com.example.individualproject.repository.GenreRepository;
import com.example.individualproject.repository.entity.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepositoryMock;

    @InjectMocks
    private GenreServiceImpl genreServiceMock;

    @Test
    void getAllGenre() {

        Genre genre1 = Genre.builder()
                .id(1l)
                .genre("GAME")
                .products( Collections.emptyList())
                .build();
        Genre genre2 = Genre.builder()
                .id(2l)
                .genre("JRPG")
                .products( Collections.emptyList())
                .build();

        GetGenreDTO genre1DTO = GetGenreDTO.builder()
                .id(genre1.getId())
                .genre(genre1.getGenre())
                .build();
        GetGenreDTO genre2DTO = GetGenreDTO.builder()
                .id(genre2.getId())
                .genre(genre2.getGenre())
                .build();

        when(genreRepositoryMock.findAll())
                .thenReturn(List.of(genre1, genre2));

        List<GetGenreDTO> actualResult = genreServiceMock.getAllGenres();

        List<GetGenreDTO> expectedResult = List.of(genre1DTO, genre2DTO);

        assertEquals(expectedResult, actualResult);

        verify(genreRepositoryMock).findAll();

    }
}