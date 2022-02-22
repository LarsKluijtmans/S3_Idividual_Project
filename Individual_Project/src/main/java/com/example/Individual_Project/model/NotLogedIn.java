package com.example.Individual_Project.model;

import com.example.Individual_Project.business.Login;
import com.example.Individual_Project.business.ViewProducts;
import com.example.Individual_Project.model.Users.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotLogedIn extends User {

    public Login login;
    public ViewProducts viewProducts;

    public NotLogedIn(int userID, String firstname, String lastname, String email, int phoneNumber, Account account,Login login, ViewProducts viewProducts) {
        super(userID, firstname, lastname, email, phoneNumber, account);

        this.login = login;
        this.viewProducts = viewProducts;
    }
}
