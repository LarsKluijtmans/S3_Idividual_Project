package com.example.Individual_Project.repository.Mongo;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImplMongo implements ProductRepository {

    DataBaseConnection database;

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getProducts(String name) {
        return null;
    }

    @Override
    public Product getProduct(int productID) {
        return null;
    }

    @Override
    public List<Product> getAllOfAUsersProducts(int userID) {
        return null;
    }

    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(int productID) {
        return false;
    }
}
