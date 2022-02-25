package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.Products.Product;

import java.util.List;

public interface ProductRepository {

     List<Product> getAllProducts();
     List<Product> getProducts(String name);
     Product getProduct(int productID);
     List<Product> getAllOfAUsersProducts(int userID);;

     boolean addProduct(Product product);

     boolean updateProduct(Product product);

     boolean deleteProduct(int productID);

}
