package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.Products.Product;

import java.util.List;

public interface DbAllProducts {    public List<Product> GetAllProducts();
     Product GetProduct(int productID);
     List<Product> GetProducts(String name);
     boolean UpdateProduct(Product product);
     boolean DeleteProduct(int productID);
}
