package com.example.individualproject.controller;

import com.example.individualproject.business.impl.LoginUseCaseImpl;
import com.example.individualproject.dto.login.LoginRequestDTO;
import com.example.individualproject.dto.login.LoginResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoginUseCaseImpl loginUseCase;

    @Test
    void login() throws Exception  {

        LoginRequestDTO loginrequest = LoginRequestDTO.builder()
                .username("worker")
                .password("worker")
                .build();

        LoginResponseDTO loginResponse = LoginResponseDTO.builder()
                .accessToken("wwqeqweqweqw")
                .authorizationLevel("NormalUser")
                .build();

        when(loginUseCase.login(loginrequest))
                .thenReturn(loginResponse);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String request = ow.writeValueAsString(loginrequest);
        String result = ow.writeValueAsString(loginResponse);

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(result));

        verify(loginUseCase).login(loginrequest);
    }
}