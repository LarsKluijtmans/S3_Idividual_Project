package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.User;

import java.util.List;

public interface DbMyProducts {
     List<Product> GetAllMyProducts(int userID);
     Product GetProduct(int productID);
     boolean AddProduct(Product product);
     boolean UpdateProduct(Product product);
     boolean DeleteProduct(int productID);
}
