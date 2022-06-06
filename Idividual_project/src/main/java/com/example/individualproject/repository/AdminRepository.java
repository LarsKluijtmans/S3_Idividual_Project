package com.example.individualproject.repository;

import com.example.individualproject.repository.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findAllByUsernameIsLike(String username);

    Admin findByUsername(String username);

    //Is username unique
    boolean existsByUsername(String name);
}
