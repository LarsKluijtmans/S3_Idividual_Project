package com.example.individualproject.controller;

import com.example.individualproject.business.impl.ProductServiceImpl;
import com.example.individualproject.dto.products.*;
import com.example.individualproject.repository.entity.NormalUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductServiceImpl productService;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    //getAllProducts
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllProducts() throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder().title("Pokemon").subTitle("diamond").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(1l).productType("GAME").images(Collections.emptyList()).build();
        BasicProductInfo product2 = BasicProductInfo.builder().title("Pokemon").subTitle("Pearl").series("Pokemon").year(2022).price(10.01).condition("TRASH").description("Pokemon game").genreId(2l).productType("GAME").images(Collections.emptyList()).build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1, null);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2, null);


        when(productService.getAllProducts())
                .thenReturn(List.of(PokemonDiamondDTO, PokemonPearlDTO));

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(PokemonDiamondDTO, PokemonPearlDTO))));

        verify(productService).getAllProducts();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllProducts_NoneFound() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getAllProducts();
    }

    //getProduct
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getProduct() throws Exception {

        BasicProductInfo product1 = BasicProductInfo.builder().
                title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME").
                images(Collections.emptyList())
                .build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1, null);

        when(productService.getProduct(1l))
                .thenReturn(PokemonDiamondDTO);

        mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(PokemonDiamondDTO)));

        verify(productService).getProduct(1l);
    }
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getProduct_NotFound() throws Exception {

        when(productService.getProduct(1l))
                .thenReturn(null);

        mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getProduct(1l);
    }

    //getAllProductsByName
    @Test
    void getAllProductsByName()throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();

        BasicProductInfo product2 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(2l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1, null);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2, null);


        when(productService.getProducts("Pokemon"))
                .thenReturn(List.of(PokemonDiamondDTO, PokemonPearlDTO));

        mockMvc.perform(get("/products/search/Pokemon"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(PokemonDiamondDTO, PokemonPearlDTO))));

        verify(productService).getProducts("Pokemon");
    }
    @Test
    void getAllProductsByName_NotFound() throws Exception {

        when(productService.getProducts("Ppppp"))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/products/search/Ppppp"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getProducts("Ppppp");
    }

    //deleteProduct
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void deleteProductNormalUser() throws Exception {

        mockMvc.perform(delete("/products/normal/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).deleteProductNormalUser(1l);
    }

    //createProduct
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void createProduct() throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();
        CreateProductRequestDTO createProductRequestDTO = CreateProductRequestDTO.builder()
                .productInfo(product1)
                .build();
        CreateProductResponseDTO createProductResponseDTO = CreateProductResponseDTO.builder()
                .productId(1l)
                .build();

        when(productService.addProduct(createProductRequestDTO))
                .thenReturn(createProductResponseDTO);

        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ow.writeValueAsString(createProductRequestDTO)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(productService).addProduct(createProductRequestDTO);
    }
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void createProduct_NotFound() throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();
        CreateProductRequestDTO createProductRequestDTO = CreateProductRequestDTO.builder()
                .productInfo(product1)
                .build();

        when(productService.addProduct(createProductRequestDTO))
                .thenReturn(null);

        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ow.writeValueAsString(createProductRequestDTO)))
                .andDo(print())
                .andExpect(status().isConflict());

        verify(productService).addProduct(createProductRequestDTO);
    }

    //updateProduct
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void updateProduct() throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();
        UpdateProductRequestDTO updateProductRequestDTO = UpdateProductRequestDTO.builder()
                .productInfo(product1)
                .build();
        UpdateProductResponseDTO updateProductResponseDTO = UpdateProductResponseDTO.builder()
                .productId(1l)
                .build();

        when(productService.updateProduct(updateProductRequestDTO))
                .thenReturn(updateProductResponseDTO);

        mockMvc.perform(put("/products")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ow.writeValueAsString(updateProductRequestDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(productService).updateProduct(updateProductRequestDTO);
    }
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void updateProduct_NotFound() throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();
        UpdateProductRequestDTO updateProductRequestDTO = UpdateProductRequestDTO.builder()
                .productInfo(product1)
                .build();

        when(productService.updateProduct(updateProductRequestDTO))
                .thenReturn(null);

        mockMvc.perform(put("/products")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ow.writeValueAsString(updateProductRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(productService).updateProduct(updateProductRequestDTO);
    }

    //deleteProduct
    @Test
    @WithMockUser(username = "me", roles = {"ADMIN"})
    void deleteProductAdmin() throws Exception {

        mockMvc.perform(delete("/products/admin/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).deleteProductAdmin(1l);
    }

    //getUsersProductsNormal
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getUsersProductsNormal()throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();

        BasicProductInfo product2 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(2l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1, null);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2, null);

        when(productService.getAllOfAUsersProductsNormalUser("Lars"))
                .thenReturn(List.of(PokemonDiamondDTO, PokemonPearlDTO));

        mockMvc.perform(get("/products/normal/Lars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(PokemonDiamondDTO, PokemonPearlDTO))));

        verify(productService).getAllOfAUsersProductsNormalUser("Lars");
    }
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getUsersProductsNormal_NotFound() throws Exception {

        when(productService.getAllOfAUsersProductsNormalUser("Lars"))
                .thenReturn(null);

        mockMvc.perform(get("/products/normal/Lars"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getAllOfAUsersProductsNormalUser("Lars");
    }

    //getUsersProductsAdmin
    @Test
    @WithMockUser(username = "me", roles = {"ADMIN"})
    void getUsersProductsAdmin()throws Exception {
        BasicProductInfo product1 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("diamond")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(1l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();

        BasicProductInfo product2 = BasicProductInfo.builder()
                .title("Pokemon")
                .subTitle("Pearl")
                .series("Pokemon")
                .year(2022)
                .price(10.01)
                .condition("TRASH")
                .description("Pokemon game")
                .genreId(2l)
                .productType("GAME")
                .images(Collections.emptyList())
                .build();

        GetProductDTO PokemonDiamondDTO = new GetProductDTO(1l, product1, null);
        GetProductDTO PokemonPearlDTO = new GetProductDTO(2l, product2, null);

        when(productService.getAllOfAUsersProductsAdmin("Lars"))
                .thenReturn(List.of(PokemonDiamondDTO, PokemonPearlDTO));

        mockMvc.perform(get("/products/admin/Lars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(PokemonDiamondDTO, PokemonPearlDTO))));

        verify(productService).getAllOfAUsersProductsAdmin("Lars");
    }
    @Test
    @WithMockUser(username = "me", roles = {"ADMIN"})
    void getUsersProductsAdmin_NotFound() throws Exception {

        when(productService.getAllOfAUsersProductsAdmin("Lars"))
                .thenReturn(null);

        mockMvc.perform(get("/products/admin/Lars"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getAllOfAUsersProductsAdmin("Lars");
    }
}