package com.example.Individual_Project.model;

import com.example.Individual_Project.model.Users.Account;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userID;
    private String firstname;
    private String lastname;
    private String email;
    private int phoneNumber;
    private Account account;
}
