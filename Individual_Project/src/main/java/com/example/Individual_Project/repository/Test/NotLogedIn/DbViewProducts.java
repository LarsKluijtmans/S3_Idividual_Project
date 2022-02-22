package com.example.Individual_Project.repository.Test.NotLogedIn;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.repository.Test.DataBase;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class DbViewProducts implements com.example.Individual_Project.repository.DbViewProducts {

    private DataBase dataBase = new DataBase();

    @Override
    public List<Product> GetAllProducts() {
        return dataBase.products;
    }

    @Override
    public Product GetProduct(int productID) {
        for(Product product: dataBase.products)
        {
            if(product.getProductID() == productID)
            {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> GetProduct(String productName) {

        ArrayList<Product> products = new ArrayList<Product>();

        for(Product product: dataBase.products)
        {
            if(product.getName1() == productName || product.getName2() == productName)
            {
                products.add(product);
            }
        }
        return products;
    }
}
