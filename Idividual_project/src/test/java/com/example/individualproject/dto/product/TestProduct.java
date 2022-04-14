package com.example.individualproject.dto.product;

import com.example.individualproject.dto.products.GetProductDTO;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;
import org.hibernate.tuple.InMemoryValueGenerationStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestProduct {

    //GetProductDTO

    @Test
    void test_GetProductDTOConstructors() {

        List<Image> images = new ArrayList<>();
        images.add(new Image(1,"gfdggdfgdfgdfgfdgfd",null));

        Product product = new Product(1l,"lars","lars","lars",2000,10.10,"GOOD","lars","JRPG",false,"GAME", images);
        GetProductDTO getProductDTO = new GetProductDTO(product);

        assertEquals("lars", getProductDTO.getProductInfo().getTitle());
        assertEquals("lars", getProductDTO.getProductInfo().getSubTitle());
        assertEquals("lars", getProductDTO.getProductInfo().getSeries());
        assertEquals(2000, getProductDTO.getProductInfo().getYear());
        assertEquals(10.10, getProductDTO.getProductInfo().getPrice());
        assertEquals("GOOD", getProductDTO.getProductInfo().getCondition());
        assertEquals("JRPG", getProductDTO.getProductInfo().getGenre());
        assertEquals("GAME", getProductDTO.getProductInfo().getProductType());
    }
}
