package com.example.Individual_Project.controller;


import com.example.Individual_Project.business.*;
import com.example.Individual_Project.model.Products.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if(products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") int id) {
        Object product = productService.getProduct(id);

        if(product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("search/{name}")
    public ResponseEntity<List<Product>> getAllProductsByName(@PathVariable("name") String name) {
        List<Product> products = productService.getProducts(name);

        if(products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        Object OldProduct = productService.getProduct(id);

        if (OldProduct == null){
            return new ResponseEntity("Please provide a valid id.", HttpStatus.BAD_REQUEST);
        }

        if(productService.updateProduct(product)) {
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity("Please provide a valid id.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (!productService.addProduct(product)){
            String entity =  "The product " + product + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "accounts/" + product.getProductID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

}
