package com.example.individualproject.DTO.Product;

import com.example.individualproject.DTO.Products.GetProductDTO;
import com.example.individualproject.DTO.Users.CreateUserRequestDTO;
import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Products {

    //GetProductDTO

    @Test
    void test_GetProductDTOConstructors() {
        Product product = new Product(1l,"lars","lars","lars",2000,10.10,"GOOD","lars","JRPG",false,"GAME" );
        GetProductDTO getProductDTO = new GetProductDTO(product);

        assertEquals("lars", getProductDTO.getTitle());
        assertEquals("lars", getProductDTO.getSub_title());
        assertEquals("lars", getProductDTO.getSeries());
        assertEquals(2000, getProductDTO.getYear());
        assertEquals(10.10, getProductDTO.getPrice());
        assertEquals("GOOD", getProductDTO.getCondition_());
        assertEquals("JRPG", getProductDTO.getGenre());
        assertEquals("GAME", getProductDTO.getProduct_type());
    }
}
