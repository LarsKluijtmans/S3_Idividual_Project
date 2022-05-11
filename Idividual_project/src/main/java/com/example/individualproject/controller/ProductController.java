package com.example.individualproject.controller;

import com.example.individualproject.configuration.security.isauthenticated.IsAuthenticated;
import com.example.individualproject.dto.products.*;
import com.example.individualproject.business.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductController {

    private final ProductService productService;

    //All
    @GetMapping()
    public ResponseEntity<List<GetProductDTO>> getAllProducts() {
        List<GetProductDTO> products = productService.getAllProducts();

        if(products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(products);
        }
    }
    @GetMapping("search/{name}")
    public ResponseEntity<List<GetProductDTO>> getAllProductsByName(@PathVariable("name") String name) {
        List<GetProductDTO> products = productService.getProducts(name);

        if(products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(products);
        }
    }

    //Normal user
    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @PostMapping()
    public ResponseEntity<GetProductDTO> createProduct(@RequestBody CreateProductRequestDTO product) {
        CreateProductResponseDTO productResponseDTO = productService.addProduct(product);

        if(productResponseDTO == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "products/" + productResponseDTO.getProductId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).build();
        }
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @PutMapping()
    public ResponseEntity<UpdateProductResponseDTO> updateProduct(@RequestBody UpdateProductRequestDTO product) {
       if(productService.updateProduct(product) != null) {
           return ResponseEntity.noContent().build();
       }else {
           return ResponseEntity.badRequest().build();
       }
    }

    //Admin and normal user
    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER", "ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<GetProductDTO> getProduct(@PathVariable("id") Long id) {
        GetProductDTO product = productService.getProduct(id);

        if(product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER", "ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
