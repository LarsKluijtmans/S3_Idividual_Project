package com.example.individualproject.controller;

import com.example.individualproject.business.impl.ProductServiceImpl;
import com.example.individualproject.dto.products.BasicProductInfo;
import com.example.individualproject.dto.products.GetProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductServiceImpl productService;

    @Test
    void getAllProducts() throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2);

        List<GetProductDTO> Result = List.of(PokemonDiamondDTO, PokemonPearlDTO);


        when(productService.getAllProducts())
                .thenReturn(Result);

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [{"id":1,"productInfo":{"title":"Pokemon","subTitle":"diamond","series":"Pokemon","year":2022,"price":10.01,"condition":"TRASH","description":"Pokemon game","genre":"JRPG","productType":"GAME","images":[]}},
                            {"id":2,"productInfo":{"title":"Pokemon","subTitle":"Pearl","series":"Pokemon","year":2022,"price":10.01,"condition":"TRASH","description":"Pokemon game","genre":"JRPG","productType":"GAME","images":[]}}]
                """ ));

        verify(productService).getAllProducts();
    }
    @Test
    void getAllProducts_NoneFound() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(null);

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getAllProducts();
    }

    @Test
    void getProduct() throws Exception {

        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1);


        when(productService.getProduct(1l))
                .thenReturn(PokemonDiamondDTO);

        mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"id":1,"productInfo":{"title":"Pokemon","subTitle":"diamond","series":"Pokemon","year":2022,"price":10.01,"condition":"TRASH","description":"Pokemon game","genre":"JRPG","productType":"GAME","images":[]}}
                 """ ));

        verify(productService).getProduct(1l);
    }
    @Test
    void getProduct_NotFound() throws Exception {

        when(productService.getProduct(1l))
                .thenReturn(null);

        mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getProduct(1l);
    }

    @Test
    void getAllProductsByName()throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genre("JRPG").productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2);

        List<GetProductDTO> Result = List.of(PokemonDiamondDTO, PokemonPearlDTO);

        when(productService.getProducts("Pokemon"))
                .thenReturn(Result);

        mockMvc.perform(get("/products/search/Pokemon"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [{"id":1,"productInfo":{"title":"Pokemon","subTitle":"diamond","series":"Pokemon","year":2022,"price":10.01,"condition":"TRASH","description":"Pokemon game","genre":"JRPG","productType":"GAME","images":[]}},
                            {"id":2,"productInfo":{"title":"Pokemon","subTitle":"Pearl","series":"Pokemon","year":2022,"price":10.01,"condition":"TRASH","description":"Pokemon game","genre":"JRPG","productType":"GAME","images":[]}}]
                """ ));

        verify(productService).getProducts("Pokemon");
    }
    @Test
    void getAllProductsByName_NotFound() throws Exception {

        when(productService.getProducts("Ppppp"))
                .thenReturn(null);

        mockMvc.perform(get("/products/search/Ppppp"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getProducts("Ppppp");
    }

    @Test
    void deleteProduct() throws Exception {

        mockMvc.perform(delete("/products/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).deleteProduct(1l);
    }

    @Test
    void createProduct() {
    }
    @Test
    void updateProduct() {
    }
}