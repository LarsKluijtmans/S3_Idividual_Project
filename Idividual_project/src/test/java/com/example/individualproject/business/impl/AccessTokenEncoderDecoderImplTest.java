package com.example.individualproject.business.impl;

import com.example.individualproject.dto.login.AccessTokenDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.security.Key;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenEncoderDecoderImplTest {

    @Mock
    private Key key;
    @InjectMocks
    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl("E91E158E4C6656F68B1B5D1C311766DE98D2AD6EF3BFB33F78E9CFCDF9");


    @Test
    void encode() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("user")
                .roles(List.of("Customer"))
                .userId(1L)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);

        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult,actualResult.substring(0,21));
    }
    @Test
    void encode_NoUserID() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("user")
                .roles(List.of("Customer"))
                .userId(null)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);

        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult,actualResult.substring(0,21));
    }
    @Test
    void encode_NoRole() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("user")
                .roles(null)
                .userId(1L)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);

        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult,actualResult.substring(0,21));
    }

}