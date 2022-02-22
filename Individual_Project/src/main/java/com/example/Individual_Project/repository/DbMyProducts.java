package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.User;

import java.util.List;

public interface DbMyProducts {    public List<Product> GetAllMyProducts(User user);
     Product GetProduct(int productID);
     boolean AddProduct(Product product);
     boolean UpdateProduct(Product product);
     boolean DeleteProduct(int productID);
}
