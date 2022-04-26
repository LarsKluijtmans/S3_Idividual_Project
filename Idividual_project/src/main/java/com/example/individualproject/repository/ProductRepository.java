package com.example.individualproject.repository;


import com.example.individualproject.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenre_GenreIsLike(String name1, String name2, String name3, String name4, String name5);

    Product findProductsByIdIs(Long id);

    List<Product> findAllBySeller_Id(Long id);
}
