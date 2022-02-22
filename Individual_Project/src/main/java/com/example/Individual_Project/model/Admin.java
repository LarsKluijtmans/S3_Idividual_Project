package com.example.Individual_Project.model;

import com.example.Individual_Project.business.AllUsers;
import com.example.Individual_Project.business.AllProducts;
import com.example.Individual_Project.business.ViewProducts;
import com.example.Individual_Project.model.Users.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends User{

    public AllProducts allProducts;
    public AllUsers allUsers;
    public ViewProducts viewProducts;

    public Admin(){
        super();
    }

    public Admin(int userID, String firstname, String lastname, String email, int phoneNumber, Account account) {
        super(userID, firstname, lastname, email, phoneNumber, account);
    }

    public Admin(int userID, String firstname, String lastname, String email, int phoneNumber, Account account, AllProducts allProducts, AllUsers allUsers, ViewProducts viewProducts) {
        super(userID, firstname, lastname, email, phoneNumber, account);

        this.allProducts = allProducts;
        this.allUsers = allUsers;
        this.viewProducts = viewProducts;
    }
}
