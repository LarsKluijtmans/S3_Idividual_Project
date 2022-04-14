package com.example.individualproject.Entity;

import com.example.individualproject.dto.products.BasicProductInfo;
import com.example.individualproject.dto.products.UpdateProductRequestDTO;
import com.example.individualproject.dto.users.CreateUserRequestDTO;
import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTests {

    @Test
    void test_ProductConstructor_UpdateProductRequestDTO() {

        UpdateProductRequestDTO requestDTO = new UpdateProductRequestDTO(1l, new BasicProductInfo( "Lars","Lars", "Lars",2022, 10.11,
                "Lars", "Lars","Lars", "Lars", Collections.emptyList()));

        Product p = new Product(requestDTO);

        assertEquals(1l, p.getId());
        assertEquals("Lars", p.getTitle());
        assertEquals("Lars", p.getSubTitle());
        assertEquals("Lars", p.getSeries());
        assertEquals(2022, p.getYear());
        assertEquals(10.11, p.getPrice());
        assertEquals("Lars", p.getCondition());
        assertEquals("Lars", p.getDescription());
        assertEquals("Lars", p.getGenre());
        assertEquals("Lars", p.getProductType());
        assertEquals(null, p.getImages());
    }
}
