package com.example.Individual_Project.repository.Test.Admin;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.repository.Test.DataBase;
import com.example.Individual_Project.repository.DbAllProducts;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class DbAllProduct implements DbAllProducts {

    private final DataBase dataBase = new DataBase();

    @Override
    public List<Product> GetAllProducts() {
        return dataBase.products;
    }

    @Override
    public Product GetProduct(int userID) {
        for(Product p: dataBase.products)
        {
            if(p.getProductID() == userID )
            {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> GetProducts(String name) {
        List<Product> products = new ArrayList<Product>();

        for(Product p: dataBase.products)
        {
            if(p.getName1() == name || p.getName2() == name)
            {
                products.add(p);
            }
        }
        return products;
    }

    @Override
    public boolean UpdateProduct(Product product) {

        for(Product p: dataBase.products)
        {
            if(p.getProductID() == product.getProductID())
            {
                p.setName1(product.getName1());
                p.setName2(product.getName2());
                p.setSerie(product.getSerie());
                p.setYear(product.getYear());
                p.setCondition(product.getCondition());
                p.setGenre(product.getGenre());
                p.setDescription(product.getDescription());
                p.setTags(product.getTags());
                p.setProductType(product.getProductType());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean DeleteProduct(int productID) {

        for(Product p: dataBase.products)
        {
            if(p.getProductID() == productID)
            {
                dataBase.products.remove(p);
                return true;
            }
        }

        return false;
    }
}
