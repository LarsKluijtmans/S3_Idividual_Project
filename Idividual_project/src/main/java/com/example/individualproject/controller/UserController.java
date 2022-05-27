package com.example.individualproject.controller;

import com.example.individualproject.configuration.security.isauthenticated.IsAuthenticated;
import com.example.individualproject.dto.users.*;
import com.example.individualproject.business.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final UserService userService;

    //Admin
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping()
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
        List<GetUserDTO> users = userService.getAllUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("{username}")
    public ResponseEntity<GetUserDTO> getUserByUsername(@PathVariable("username") String username) {
        GetUserDTO user = userService.getUserByName(username);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping( "/search/{name}")
    public ResponseEntity<List<GetUserDTO>> getAllUserByName(@PathVariable("name") String name) {
        List<GetUserDTO> users = userService.getAllUserByName(name);

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/NormalUser")
    public ResponseEntity<List<GetUserDTO>> getAllNormalUser() {
        List<GetUserDTO> users = userService.getAllNormalUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/Admin")
    public ResponseEntity<List<GetUserDTO>> getAllAdmin() {
        List<GetUserDTO> users = userService.getAllAdmins();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String userName) {
        userService.deleteUser(userName);
        return ResponseEntity.ok().build();
    }

    //NormalUser
    @IsAuthenticated
    @RolesAllowed({"ROLE_NORMALUSER"})
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
