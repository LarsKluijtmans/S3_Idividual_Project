package com.example.individualproject.repository;

import com.example.individualproject.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findAllBySold(boolean sold);

    //todo is this the right way to do this, should I limit the amount of rows returned, it said something about it in OWASP: 2 Insertion
    @Query("select p from Product p WHERE p.title like ?1 AND  p.sold = false OR p.subTitle like ?1 AND p.sold = false")
    List<Product> findProductsByTitleOrSubTitleAndSold(String name);

    Product findProductsByIdIsAndSold(Long id, boolean sold);

    Product findProductsByIdIs(Long id);

    List<Product> findAllBySeller_Id(Long id);
}
