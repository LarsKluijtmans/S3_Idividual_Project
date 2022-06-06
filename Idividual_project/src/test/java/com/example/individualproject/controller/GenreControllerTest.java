package com.example.individualproject.controller;

import com.example.individualproject.business.GenreService;
import com.example.individualproject.dto.genre.GetGenreDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    //getAllGenres
    @Test
    void getAllGenre() throws Exception {

        GetGenreDTO genre1DTO = GetGenreDTO.builder()
                .id(1L)
                .genre("GAME")
                .build();

        GetGenreDTO genre2DTO = GetGenreDTO.builder()
                .id(2L)
                .genre("JRPG")
                .build();

        when(genreServiceMock.getAllGenres())
                .thenReturn(List.of(genre1DTO, genre2DTO));

        mockMvc.perform(get("/genre/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(genre1DTO, genre2DTO))));

        verify(genreServiceMock).getAllGenres();
    }

    @Test
    void getAllGenre_NothingFound() throws Exception {
        when(genreServiceMock.getAllGenres())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/genre/"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(genreServiceMock).getAllGenres();
    }

    //getGenreByName
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})

    void getGenreByName() throws Exception {

        GetGenreDTO genre1DTO = GetGenreDTO.builder()
                .id(1L)
                .genre("GAME")
                .build();

        when(genreServiceMock.getByName("GAME"))
                .thenReturn(genre1DTO);

        mockMvc.perform(get("/genre/GAME"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(genre1DTO)));

        verify(genreServiceMock).getByName("GAME");
    }

    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getGenreByName_NothingFound() throws Exception {
        when(genreServiceMock.getByName("GAME"))
                .thenReturn(null);

        mockMvc.perform(get("/genre/GAME"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(genreServiceMock).getByName("GAME");
    }
}