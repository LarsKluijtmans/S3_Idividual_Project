package com.example.Individual_Project.business;

import com.example.Individual_Project.model.Products.Product;

import java.util.List;

public interface ViewProducts {

     List<Product> GetAllProducts();
     Product GetProduct(int productID);
     List<Product> GetProduct(String productName);
}
