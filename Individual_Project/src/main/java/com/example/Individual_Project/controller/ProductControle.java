package com.example.Individual_Project.controller;


import com.example.Individual_Project.business.*;
import com.example.Individual_Project.model.Products.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControle {


    private final MyProducts myProducts;
    private final AllProducts allProducts;
    private final ViewProducts viewProducts;

    public ProductControle(MyProducts myProducts, AllProducts allProducts, ViewProducts viewProducts)
    {
        this.myProducts = myProducts;
        this.allProducts = allProducts;
        this.viewProducts = viewProducts;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> GetAllUsers() {
        List<Product> products = viewProducts.GetAllProducts();

        if(products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
