package com.example.Individual_Project.business.Admin;

import com.example.Individual_Project.business.AllProducts;
import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.repository.Test.Admin.DbAllProduct;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class AllProduct implements AllProducts {

    private final DbAllProduct dbAllProduct;

    public AllProduct(DbAllProduct dbAllProduct) {
        this.dbAllProduct = dbAllProduct;
    }

    @Override
    public List<Product> GetAllProducts() {
        return dbAllProduct.GetAllProducts();
    }

    @Override
    public Product GetProduct(int userID) {
        return dbAllProduct.GetProduct(userID);
    }

    @Override
    public List<Product> GetProducts(String name) {
        return dbAllProduct.GetProducts(name);
    }

    @Override
    public boolean UpdateProduct(Product product) {
        return dbAllProduct.UpdateProduct(product);
    }

    @Override
    public boolean DeleteProduct(int productID) {
        return dbAllProduct.DeleteProduct(productID);
    }
}
