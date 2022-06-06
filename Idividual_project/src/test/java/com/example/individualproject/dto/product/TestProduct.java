package com.example.individualproject.dto.product;

import com.example.individualproject.dto.products.GetProductDTO;
import com.example.individualproject.repository.entity.Genre;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestProduct {

    //GetProductDTO

    @Test
    void test_GetProductDTOConstructors() {

        List<Image> images = new ArrayList<>();
        images.add(new Image(1L,"url",null));

        Product product = new Product(1L,"lars","lars","lars",2000,10.10,"GOOD","lars",new Genre(59L,"JRPG", null),false,"GAME", images, new NormalUser());
        GetProductDTO getProductDTO = new GetProductDTO(product);

        assertEquals("lars", getProductDTO.getTitle());
        assertEquals("lars", getProductDTO.getSubTitle());
        assertEquals("lars", getProductDTO.getSeries());
        assertEquals(2000, getProductDTO.getYear());
        assertEquals(10.10, getProductDTO.getPrice());
        assertEquals("GOOD", getProductDTO.getCondition());
        assertEquals("JRPG", getProductDTO.getGenre());
        assertEquals("GAME", getProductDTO.getProductType());
    }
}
