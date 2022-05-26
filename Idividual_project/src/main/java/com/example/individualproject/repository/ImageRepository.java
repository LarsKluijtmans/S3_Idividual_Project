package com.example.individualproject.repository;


import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteByProductId(Long productId);
}
