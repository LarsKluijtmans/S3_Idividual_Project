package com.example.individualproject.dto.genre;

import com.example.individualproject.repository.entity.Genre;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GetGenreDTOTest {
    @Test
    void test_GetGenreDTOConstructors() {

        Genre genre = new Genre(1l, "JRPG", Collections.emptyList());
        GetGenreDTO getGenreDTO = new GetGenreDTO(genre);

        assertEquals(1l, getGenreDTO.getId());
        assertEquals("JRPG", getGenreDTO.getGenre());
    }
}