package com.example.individualproject.repository;


import com.example.individualproject.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenreIsLike(String name1,String name2,String name3,String name4,String name5);
    Product findProductsByIdIsLike(Long id);


}
