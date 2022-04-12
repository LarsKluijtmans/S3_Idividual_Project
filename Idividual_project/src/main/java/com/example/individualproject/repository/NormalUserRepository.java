package com.example.individualproject.repository;

import com.example.individualproject.repository.entity.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser, Long>  {

    List<NormalUser> findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike(String firstName,String lastName,String userName);
    NormalUser getUserByUsernameIsLikeAndPasswordIsLike(String username, String password);
    NormalUser findAllByIdIsLike(Long id);

    //Username, email, phone-number unique?
    NormalUser findByUsernameIsLike(String name);
    NormalUser findByEmailIsLike(String name);
    NormalUser findByPhonenumberIsLike(String name);
}
