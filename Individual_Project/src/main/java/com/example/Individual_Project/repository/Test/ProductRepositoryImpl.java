package com.example.Individual_Project.repository.Test;

import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Primary
@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private DataBase dataBase = new DataBase();

    @Override
    public List<Product> getAllProducts() {
        return dataBase.products;
    }
    @Override
    public List<Product> getProducts(String name) {
        List<Product> products = new ArrayList<>();

        for (Product p: dataBase.products) {
            if(p.getName1().equals(name))
            {
                products.add(p);
            }
        }
        return products;
    }
    @Override
    public List<Product> getAllOfAUsersProducts(int userID) {
        List<Product> products = new ArrayList<Product>();

        for (Product p : dataBase.products) {
            if (p.getUser().getUserID() == userID) {
                products.add(p);
            }
        }
        return products;
    }
    @Override
    public Product getProduct(int productID) {
        for (Product p : dataBase.products) {
            if (p.getProductID() == productID) {
                return p;
            }
        }
        return null;
    }
    @Override
    public boolean addProduct(Product product) {

        if (product == null) {
            return false;
        }
        if (dataBase.products.contains(product)) {
            return false;
        }

        int count = dataBase.products.size();

        dataBase.products.add(product);

        if (dataBase.products.size() > count) {
            return true;
        }
        return false;
    }
    @Override
    public boolean updateProduct(Product product) {
        for (Product p : dataBase.products) {
            if (p.getProductID() == product.getProductID()) {
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
    public boolean deleteProduct(int productID) {

        for (Product p : dataBase.products) {
            if (p.getProductID() == productID) {
                dataBase.products.remove(p);
                return true;
            }
        }

        return false;
    }
}
