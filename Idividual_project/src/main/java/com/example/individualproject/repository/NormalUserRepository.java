package com.example.individualproject.repository;

import com.example.individualproject.repository.entity.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser, Long>  {

    List<NormalUser> findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike(String firstName,String lastName,String userName);
    NormalUser getUserByUsernameIsAndPasswordIs(String username, String password);
    NormalUser findAllByIdIs(Long id);
    NormalUser findByUsername(String username);

    boolean existsByUsername(String name);
    boolean existsByEmail(String name);
    boolean existsByPhonenumber(String name);

}
