package com.example.individualproject.repository;

import com.example.individualproject.repository.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>  {

    List<Admin> findAllByUsernameIsLike(String firstName);
    Admin getUserByUsernameIsAndPasswordIs(String username, String password);
    Admin findAllByIdIsLike(Long id);

    //Is username unique
    Admin findByUsernameIsLike(String name);
    Admin findByUsernameIs(String name);
}
