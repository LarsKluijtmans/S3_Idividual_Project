package com.example.individualproject.Entity;

import com.example.individualproject.repository.entity.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTests {

    @Test
    void test_NormalUserConstructors() {
        Admin user = new Admin("Lars", "Lars");

        assertEquals("Lars", user.getUsername());
        assertEquals("Lars", user.getPassword());
    }
}
