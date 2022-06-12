package com.example.individualproject.controller;

import com.example.individualproject.business.ProductService;
import com.example.individualproject.configuration.security.isauthenticated.IsAuthenticated;
import com.example.individualproject.dto.products.*;
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

        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(products);
        }
    }

    @GetMapping("search/{name}")
    public ResponseEntity<List<GetProductDTO>> getAllProductsByName(@PathVariable("name") String name) {
        List<GetProductDTO> products = productService.getProducts(name);

        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(products);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProductDTO> getProduct(@PathVariable("id") Long id) {
        GetProductDTO product = productService.getProduct(id);

        if (product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Normal user
    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @PostMapping()
    public ResponseEntity<CreateProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO product) {
        CreateProductResponseDTO productResponseDTO = productService.addProduct(product);

        if (productResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "products/" + productResponseDTO.getProductId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(productResponseDTO);
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @PutMapping()
    public ResponseEntity<UpdateProductResponseDTO> updateProduct(@RequestBody UpdateProductRequestDTO product) {
        if (productService.updateProduct(product) != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @DeleteMapping("normal/{id}")
    public ResponseEntity<Object> deleteProductNormalUser(@PathVariable("id") Long id) {
        productService.deleteProductNormalUser(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @GetMapping("normal/{username}")
    public ResponseEntity<List<GetProductDTO>> getUsersProductsNormal(@PathVariable("username") String username) {
        List<GetProductDTO> usersProducts = productService.getAllOfAUsersProductsNormalUser(username);

        if (usersProducts != null) {
            return ResponseEntity.ok().body(usersProducts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
    @PutMapping("buy/{productID}")
    public ResponseEntity<List<GetProductDTO>> buyProduct(@PathVariable("productID") Long productID) {
        productService.buyProduct(productID);

        return ResponseEntity.ok().build();
    }


    //Admin
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("admin/{id}")
    public ResponseEntity<Object> deleteProductAdmin(@PathVariable("id") Long id) {
        productService.deleteProductAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("admin/{username}")
    public ResponseEntity<List<GetProductDTO>> getUsersProductsAdmin(@PathVariable("username") String username) {
        List<GetProductDTO> usersProducts = productService.getAllOfAUsersProductsAdmin(username);

        if (usersProducts != null) {
            return ResponseEntity.ok().body(usersProducts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
