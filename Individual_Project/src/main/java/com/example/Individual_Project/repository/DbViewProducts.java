package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.Products.Product;

import java.util.List;

public interface DbViewProducts {
     List<Product> GetAllProducts();
     Product GetProduct(int productID);
     List<Product> GetProduct(String productName);
}
