package com.example.individualproject.business;

import com.example.individualproject.dto.products.*;

import java.util.List;


public interface ProductService {

    List<GetProductDTO> getAllProducts();

    List<GetProductDTO> getProducts(String name);

    GetProductDTO getProduct(Long productID);

    CreateProductResponseDTO addProduct(CreateProductRequestDTO product);

    UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO product);

    void deleteProductAdmin(Long productID);

    void deleteProductNormalUser(Long productID);

    List<GetProductDTO> getAllOfAUsersProductsAdmin(String username);

    List<GetProductDTO> getAllOfAUsersProductsNormalUser(String username);

    boolean buyProduct(Long productID);
}
