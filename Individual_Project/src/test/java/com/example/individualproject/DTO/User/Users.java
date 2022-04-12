package com.example.individualproject.DTO.User;

import com.example.individualproject.DTO.Products.GetProductDTO;
import com.example.individualproject.DTO.Users.GetUserDTO;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Users {

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

        Admin user = new Admin("lars","lars");
        GetUserDTO getUserDTO = new GetUserDTO(user);

        assertEquals("lars", getUserDTO.getUsername());
    }
}
