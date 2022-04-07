package com.example.Individual_Project.business.Impl;

import com.example.Individual_Project.business.ProductService;
import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
    @Override
    public List<Product> getProducts(String name) {
      if(name != "" && name != null) {
          return productRepository.getProducts(name);
      }

      return new ArrayList<Product>();
    }
    @Override
    public Product getProduct(int productID) {
        return productRepository.getProduct(productID);
    }
    @Override
    public List<Product> getAllOfAUsersProducts(int userID) {
        return productRepository.getAllOfAUsersProducts(userID);
    }
    @Override
    public boolean addProduct(Product product) {
        return productRepository.addProduct(product);
    }
    @Override
    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }
    @Override
    public boolean deleteProduct(int productID) {
        return productRepository.deleteProduct(productID);
    }
}
