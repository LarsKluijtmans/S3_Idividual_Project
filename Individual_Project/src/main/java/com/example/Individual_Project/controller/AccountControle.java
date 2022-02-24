package com.example.Individual_Project.controller;

import com.example.Individual_Project.business.AllUsers;
import com.example.Individual_Project.business.MyAccount;
import com.example.Individual_Project.model.NormalUser;
import com.example.Individual_Project.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountControle {

    private final MyAccount myAccount;
    private final AllUsers allUsers;

    public AccountControle(MyAccount myAccount, AllUsers allUsers)
    {
        this.myAccount = myAccount;
        this.allUsers = allUsers;
    }

    @GetMapping()
    public ResponseEntity<List<User>> GetAllUsers() {
        List<User> users = allUsers.GetAllAccounts();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUsersByID(@PathVariable("id") int id) {
        User user = allUsers.GetAccount(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id) {
        allUsers.DeleteAccount(id);
        return ResponseEntity.ok().build();
    }




     @GetMapping( "search/{name}")
    public ResponseEntity<List<User>> getUsers(@PathVariable("name") String name) {
        List<User> users = allUsers.GetAllAccounts(name);

        if(users.stream().count() != 0) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody NormalUser user) {
        if (!allUsers.AddAccount(user)){
            String entity =  "The user " + user + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "accounts/" + user.getUserID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateStudent(@PathVariable("id") int id, @RequestBody NormalUser user) {
        User OldUser = allUsers.GetAccount(id);

        if (OldUser == null){
            return new ResponseEntity("Please provide a valid id.",HttpStatus.BAD_REQUEST);
        }

       if( myAccount.UpdateAccount(user)) {
           return ResponseEntity.noContent().build();
       }else {
           return new ResponseEntity("Please provide a valid id.", HttpStatus.BAD_REQUEST);
       }
    }
}
