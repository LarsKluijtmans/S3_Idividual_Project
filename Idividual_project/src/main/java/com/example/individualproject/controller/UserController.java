package com.example.individualproject.controller;

import com.example.individualproject.business.ProductService;
import com.example.individualproject.dto.products.GetProductDTO;
import com.example.individualproject.dto.users.*;
import com.example.individualproject.business.UserService;
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
    private final ProductService productService;

     //Admin
    @GetMapping()
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
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

        if(users != null) {
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
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    //NormalUser
    @GetMapping("products/{id}")
    public ResponseEntity<List<GetProductDTO>> getUsersProducts( @PathVariable("id") Long id) {
        List<GetProductDTO> usersProducts = productService.getAllOfAUsersProducts(id);

        if(usersProducts != null) {
            return ResponseEntity.ok().body(usersProducts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping()
    public ResponseEntity<UpdateUserResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO user) {
        UpdateUserResponseDTO responseDTO = userService.updateUser(user);

       if(responseDTO != null) {
           return ResponseEntity.ok().body(responseDTO);
       }else {
           return ResponseEntity.badRequest().build();
       }
    }

    //All
    @GetMapping("login/{username}/{password}")
    public ResponseEntity<GetLoginDTO> login( @PathVariable("username") String username, @PathVariable("password") String password) {
        GetLoginDTO user = userService.VerifyLoginCredentails(username,password);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping( "unique/name/{name}")
    public ResponseEntity<Boolean> isUsernameUnique(@PathVariable("name") String name) {
        Boolean result = userService.isUsernameUnique(name);

        return ResponseEntity.ok().body(result);
    }
    @GetMapping( "unique/phoneNumber/{phoneNumber}")
    public ResponseEntity<Boolean> isPhoneNumberUnique(@PathVariable("phoneNumber") String phoneNumber) {
        Boolean result = userService.isPhoneNumberUnique(phoneNumber);

        return ResponseEntity.ok().body(result);
    }
    @GetMapping( "unique/email/{email}")
    public ResponseEntity<Boolean> isEmailUnique(@PathVariable("email") String email) {
        Boolean result = userService.isEmailUnique(email);

        return ResponseEntity.ok().body(result);
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

}
