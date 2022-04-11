package com.example.individualproject.repository;


import com.example.individualproject.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

     List<Product> findProductsByTitleLikeOrSub_titleLike(String name, String name2);

     List<Product> findAllBy(String name);
}
