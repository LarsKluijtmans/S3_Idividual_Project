package com.example.Individual_Project.model;

import com.example.Individual_Project.model.Users.Account;

import lombok.*;

@Getter
@Setter
public abstract class User {

    private int userID;
    private String firstname;
    private String lastname;
    private String email;
    private int phoneNumber;
    private Account account;

    public User(){}

    public User(int userID, String firstname, String lastname, String email, int phoneNumber, Account account) {

        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }
}
