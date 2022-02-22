package com.example.Individual_Project.repository.Test.NormalUser;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.Test.DataBase;
import com.example.Individual_Project.repository.DbMyProducts;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class DbMyPorduct implements DbMyProducts {

    private DataBase dataBase = new DataBase();

    @Override
    public List<Product> GetAllMyProducts(User user) {
        List<Product> myPorducts = new ArrayList<Product>();

        for(Product p: dataBase.products)
        {
            if(p.getUser() == user)
            {
                myPorducts.add(p);
            }
        }

        return myPorducts;
    }

    @Override
    public Product GetProduct(int productID) {
        for(Product p: dataBase.products)
         {
            if(p.getProductID() == productID)
            {
                return p;
            }
         }
        return null;
    }

    @Override
    public boolean AddProduct(Product product) {

        if(product == null)
        {
            return false;
        }
        if(dataBase.products.contains(product))
        {
            return false;
        }

        int count = dataBase.products.size();

        dataBase.products.add(product);

        if(dataBase.products.size() > count)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean UpdateProduct(Product product) {

        if(!dataBase.products.contains(product))
        {
            return false;
        }
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
