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

        Genre genre1 = new Genre(1l, "GAME", Collections.emptyList());
        Genre genre2 = new Genre(2l, "JRPG", Collections.emptyList());

        when(genreRepositoryMock.findAll())
                .thenReturn(List.of(genre1, genre2));

        List<GetGenreDTO> actualResult = genreServiceMock.getAllGenres();

        GetGenreDTO genre1DTO = new GetGenreDTO(genre1);
        GetGenreDTO genre2DTO = new GetGenreDTO(genre2);
        List<GetGenreDTO> expectedResult = List.of(genre1DTO, genre2DTO);

        assertEquals(expectedResult, actualResult);

        verify(genreRepositoryMock).findAll();

    }

}