package com.example.individualproject.dto.login;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenDTOTest {

    @Test
    void hasRole() {
        AccessTokenDTO result = new AccessTokenDTO("name", List.of("NORMALUSER"), 1L);

        assertTrue(result.hasRole("NORMALUSER"));
        assertFalse(result.hasRole("ADMIN"));
    }

    @Test
    void hasRole_Null() {
        AccessTokenDTO result = new AccessTokenDTO("name", null, 1L);

        assertFalse(result.hasRole("NORMALUSER"));
    }
}