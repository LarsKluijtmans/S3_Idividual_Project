package com.example.Individual_Project.business.NormalUser;

import com.example.Individual_Project.business.MyProducts;
import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.DbMyProducts;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class MyPorduct implements MyProducts {

    private final DbMyProducts dbMyProducts;

    public MyPorduct( DbMyProducts dbMyProducts) {
        this.dbMyProducts = dbMyProducts;
    }

    @Override
    public List<Product> GetAllMyProducts(User user) {
        return dbMyProducts.GetAllMyProducts(user);
    }

    @Override
    public Product GetProduct(int productID) {
        return dbMyProducts.GetProduct(productID);
    }

    @Override
    public boolean AddProduct(Product product) {
        return dbMyProducts.AddProduct(product);
    }

    @Override
    public boolean UpdateProduct(Product product) {
        return dbMyProducts.UpdateProduct(product);
    }

    @Override
    public boolean DeleteProduct(int productID) {
        return dbMyProducts.DeleteProduct(productID);
    }
}
