package com.example.individualproject.dto.product;

import com.example.individualproject.dto.products.GetProductDTO;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestProduct {

    //GetProductDTO

    @Test
    void test_GetProductDTOConstructors() {
        Product product = new Product(1l,"lars","lars","lars",2000,10.10,"GOOD","lars","JRPG",false,"GAME", Collections.emptyList());
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
