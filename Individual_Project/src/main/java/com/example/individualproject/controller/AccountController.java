package com.example.individualproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class AccountController {

    /* private final AccountService accountService;

  @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = accountService.getAllAccounts();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUsersByID(@PathVariable("id") int id) {
        User user = accountService.getAccount(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("login/{username}/{password}")
    public ResponseEntity<User> getUsersByUsernameAndPassword( @PathVariable String password, @PathVariable String username) {
      Account a = new Account(username,password);

       User user = accountService.getAccount(a);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping( "search/{name}")
    public ResponseEntity<List<User>> getUsers(@PathVariable("name") String name) {
        List<User> users = accountService.getAllAccounts(name);

        if(users.stream().count() != 0) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody NormalUser user) {
        if (!accountService.addAccount(user)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "accounts/" + user.getUserID();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(user);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody NormalUser user) {
        User oldUser = accountService.getAccount(id);

        if (oldUser == null){
            return ResponseEntity.badRequest().build();
        }

       if( accountService.updateAccount(user)) {
           return ResponseEntity.noContent().build();
       }else {
           return ResponseEntity.badRequest().build();
       }
    }*/
}
