package com.example.individualproject.controller;

import com.example.individualproject.DTO.Users.*;
import com.example.individualproject.business.UserService;
import com.example.individualproject.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

     private final UserService userService;

     //Admin
    @GetMapping()
    public ResponseEntity<List<GetUserDTO>> getAllUsersById() {
        List<GetUserDTO> users = userService.getAllUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<GetUserDTO> getUserByID(@PathVariable("id") Long id) {
        GetUserDTO user = userService.getUserByID(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping( "search/{name}")
    public ResponseEntity<List<GetUserDTO>> getAllUserByName(@PathVariable("name") String name) {
        List<GetUserDTO> users = userService.getAllUserByName(name);

        if(users.stream().count() != 0) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("NormalUser")
    public ResponseEntity<List<GetUserDTO>> getAllNormalUser() {
        List<GetUserDTO> users = userService.getAllNormalUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("Admin")
    public ResponseEntity<List<GetUserDTO>> getAllAdmin() {
        List<GetUserDTO> users = userService.getAllAdmins();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


    //NormalUser
    @GetMapping("login/{username}/{password}")
    public ResponseEntity<GetUserDTO> getUsersByUsernameAndPassword( @PathVariable("username") String username, @PathVariable("password") String password) {
        UserAccountRequestDTO account = new UserAccountRequestDTO(username,password);

        GetUserDTO user = userService.getUser(account);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO user) {

        CreateUserResponseDTO userResponseDTO = userService.addUser(user);
         if (userResponseDTO != null){
            String url = "Unknown";
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(userResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PutMapping()
    public ResponseEntity<UpdateUserResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO user) {
        UpdateUserResponseDTO responseDTO = userService.updateUser(user);

       if(responseDTO != null) {
           return ResponseEntity.noContent().build();
       }else {
           return ResponseEntity.badRequest().build();
       }
    }
}
