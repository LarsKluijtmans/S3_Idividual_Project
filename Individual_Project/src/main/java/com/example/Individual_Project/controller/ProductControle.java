package com.example.Individual_Project.controller;


import com.example.Individual_Project.business.*;
import com.example.Individual_Project.model.NormalUser;
import com.example.Individual_Project.model.Products.Product;

import com.example.Individual_Project.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Product>> GetAllProducts() {
        List<Product> products = viewProducts.GetAllProducts();

        if(products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> GetProduct(@PathVariable("id") int id) {
        Product product = viewProducts.GetProduct(id);

        if(product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("search/{name}")
    public ResponseEntity<List<Product>> GetAllProductsByName(@PathVariable("name") String name) {
        List<Product> products = viewProducts.GetProduct(name);

        if(products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity UpdateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        Product OldProduct = allProducts.GetProduct(id);

        if (OldProduct == null){
            return new ResponseEntity("Please provide a valid id.", HttpStatus.BAD_REQUEST);
        }

        if(allProducts.UpdateProduct(product)) {
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity("Please provide a valid id.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity DeleteProduct(@PathVariable("id") int id) {
        allProducts.DeleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (!myProducts.AddProduct(product)){
            String entity =  "The product " + product + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "accounts/" + product.getProductID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

}
