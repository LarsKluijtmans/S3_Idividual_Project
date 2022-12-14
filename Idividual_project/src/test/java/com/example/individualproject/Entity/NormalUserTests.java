package com.example.individualproject.Entity;

import com.example.individualproject.dto.users.CreateUserRequestDTO;
import com.example.individualproject.repository.entity.NormalUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NormalUserTests {

    @Test
    void test_NormalUserConstructorS() {
        NormalUser user = new NormalUser();

        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getFirstname());
        assertNull(user.getLastname());
        assertNull(user.getPhonenumber());
        assertNull(user.getEmail());

        //Create normal user using CreateDTO
        CreateUserRequestDTO createUser = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars" );
         user = new NormalUser(createUser);

        assertEquals(user.getUsername(), createUser.getUsername());
        assertEquals(user.getPassword(), createUser.getPassword());
        assertEquals(user.getFirstname(), createUser.getFirstName());
        assertEquals(user.getLastname(), createUser.getLastName());
        assertEquals(user.getPhonenumber(), createUser.getPhoneNumber());
        assertEquals(user.getEmail(), createUser.getEmail());

        //Create normal user using 6 strings
        user = new NormalUser( "lars1","lars1","lars1","lars1","lars1","lars1", null );

        assertEquals("lars1", user.getUsername());
        assertEquals("lars1", user.getPassword());
        assertEquals("lars1", user.getFirstname());
        assertEquals("lars1", user.getLastname());
        assertEquals("lars1", user.getPhonenumber() );
        assertEquals("lars1", user.getEmail());

        //Create normal user using 6 strings and 1 long
        user = new NormalUser(1L, "lars2","lars2","lars2","lars2","lars2","lars2" );

        assertEquals(1L, user.getId());
        assertEquals("lars2", user.getUsername());
        assertEquals("lars2", user.getPassword());
        assertEquals("lars2", user.getFirstname());
        assertEquals("lars2", user.getLastname());
        assertEquals("lars2", user.getPhonenumber());
        assertEquals("lars2", user.getEmail());
    }
}
