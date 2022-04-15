package com.example.individualproject.dto.user;

import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestUsers {

    //GetProductDTO

    @Test
    void test_GetUserDTOConstructorNoramlUser() {

        NormalUser user = new NormalUser("lars","lars","lars","lars","lars","lars"  );
        GetUserDTO getUserDTO = new GetUserDTO(user);

        assertEquals("lars", getUserDTO.getUsername());
        assertEquals("lars", getUserDTO.getFirstName());
        assertEquals("lars", getUserDTO.getLastName());
        assertEquals("lars", getUserDTO.getEmail());
        assertEquals("lars", getUserDTO.getPhoneNumber());
    }
    @Test
    void test_GetUserDTOConstructorAdmin() {

        Admin user = new Admin(1l, "lars","lars");
        GetUserDTO getUserDTO = new GetUserDTO(user);

        assertEquals("lars", getUserDTO.getUsername());
    }
}
