package com.example.Individual_Project.business.NotLogedIn;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.repository.DbViewProducts;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class ViewProducts implements com.example.Individual_Project.business.ViewProducts {

    private final DbViewProducts dbViewProducts;

    public ViewProducts( DbViewProducts dbViewProducts) {
        this.dbViewProducts = dbViewProducts;
    }

    @Override
    public List<Product> GetAllProducts() {
        return dbViewProducts.GetAllProducts();
    }

    @Override
    public Product GetProduct(int productID) {
        return dbViewProducts.GetProduct(productID);
    }

    @Override
    public List<Product> GetProduct(String productName) {

        return dbViewProducts.GetProduct(productName);
    }
}
