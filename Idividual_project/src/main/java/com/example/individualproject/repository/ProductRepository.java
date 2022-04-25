package com.example.individualproject.repository;


import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.Product;
import com.example.individualproject.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByTitleLikeOrSubTitleIsLikeOrSeriesIsLikeOrConditionIsLikeOrGenreIsLike(String name1, String name2, String name3, String name4, String name5);

    Product findProductsByIdIs(Long id);

   // @Query("SELECT * FROM product p WHERE p.seller = ?1")
    List<Product> findAllBySeller_Id(Long id);
}
