package com.example.individualproject.controller;


import com.example.individualproject.DTO.*;
import com.example.individualproject.business.*;
import com.example.individualproject.repository.entity.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductController {

    private final ProductService productService;

    //Works
    @GetMapping()
    public ResponseEntity<List<GetProductDTO>> getAllProducts() {
        List<GetProductDTO> products = productService.getAllProducts();

        if(products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<GetProductDTO>> getProduct(@PathVariable("id") Long id) {
        Optional<GetProductDTO> product = productService.getProduct(id);

        if(product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
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
    @GetMapping("search/{name}")
    public ResponseEntity<List<GetProductDTO>> getAllProductsByName(@PathVariable("name") String name) {
        List<GetProductDTO> products = productService.getProducts(name);

        if(products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


    //ToDo
    @PutMapping()
    public ResponseEntity<UpdateProductResponseDTO> updateProduct(@RequestBody UpdateProductRequestDTO product) {
        if(productService.updateProduct(product) != null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }


}
