package com.example.individualproject.business;

import com.example.individualproject.DTO.*;
import com.example.individualproject.repository.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

     List<GetProductDTO> getAllProducts();
     List<GetProductDTO> getProducts(String name);
     Optional<GetProductDTO> getProduct(Long productID);
     List<GetProductDTO> getAllOfAUsersProducts(Long userID);

     CreateProductResponseDTO addProduct(CreateProductRequestDTO product);

     UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO product);

     void deleteProduct(Long productID);
}
