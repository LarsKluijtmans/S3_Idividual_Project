package com.example.Individual_Project.business;

import com.example.Individual_Project.model.Products.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {

     List<Product> getAllProducts();
     List<Product> getProducts(String name);
     Product getProduct(int productID);
     List<Product> getAllOfAUsersProducts(int userID);;

     boolean addProduct(Product product);

     boolean updateProduct(Product product);

     boolean deleteProduct(int productID);
}
