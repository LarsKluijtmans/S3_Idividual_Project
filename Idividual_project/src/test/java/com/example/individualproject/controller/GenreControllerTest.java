package com.example.individualproject.controller;

import com.example.individualproject.business.GenreService;
import com.example.individualproject.dto.genre.GetGenreDTO;
import com.example.individualproject.repository.entity.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreServiceMock;

    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getAllGenre() throws Exception {

        Genre genre1 = new Genre(1l, "GAME", Collections.emptyList());
        Genre genre2 = new Genre(2l, "JRPG", Collections.emptyList());

        GetGenreDTO genre1DTO = new GetGenreDTO(genre1);
        GetGenreDTO genre2DTO = new GetGenreDTO(genre2);

        when(genreServiceMock.getAllGenres())
                .thenReturn(List.of(genre1DTO, genre2DTO));

        mockMvc.perform(get("/genre/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [{"id":1,"genre":"GAME"},{"id":2,"genre":"JRPG"}]
                """ ));

        verify(genreServiceMock).getAllGenres();
    }

    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getAllGenre_NothingFound() throws Exception {
        when(genreServiceMock.getAllGenres())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/genre/"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(genreServiceMock).getAllGenres();
    }
}