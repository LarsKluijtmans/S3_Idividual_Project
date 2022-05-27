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

    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @DeleteMapping("normal/{id}")
    public ResponseEntity<Object> deleteProductNormalUser(@PathVariable("id") Long id) {
        productService.deleteProductNormalUser(id);
        return ResponseEntity.ok().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @GetMapping("normal/{id}")
    public ResponseEntity<List<GetProductDTO>> getUsersProductsNormal( @PathVariable("id") Long id) {
        List<GetProductDTO> usersProducts = productService.getAllOfAUsersProductsNormalUser(id);

        if(usersProducts != null) {
            return ResponseEntity.ok().body(usersProducts);
        } else {
            return ResponseEntity.notFound().build();
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


    //Admin
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("admin/{id}")
    public ResponseEntity<Object> deleteProductAdmin(@PathVariable("id") Long id) {
        productService.deleteProductAdmin(id);
        return ResponseEntity.ok().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("admin/{id}")
    public ResponseEntity<List<GetProductDTO>> getUsersProductsAdmin( @PathVariable("id") Long id) {
        List<GetProductDTO> usersProducts = productService.getAllOfAUsersProductsAdmin(id);

        if(usersProducts != null) {
            return ResponseEntity.ok().body(usersProducts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
