package com.example.individualproject.business;

import com.example.individualproject.DTO.Products.*;

import java.util.List;

public interface ProductService {

     List<GetProductDTO> getAllProducts();
     List<GetProductDTO> getProducts(String name);
     GetProductDTO getProduct(Long productID);
     List<GetProductDTO> getAllOfAUsersProducts(Long userID);

     CreateProductResponseDTO addProduct(CreateProductRequestDTO product);

     UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO product);

     void deleteProduct(Long productID);
}
