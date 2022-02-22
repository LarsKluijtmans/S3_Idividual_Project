package com.example.Individual_Project.model;

import com.example.Individual_Project.business.MyAccount;
import com.example.Individual_Project.business.MyProducts;
import com.example.Individual_Project.business.ViewProducts;
import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.model.Users.Address;
import com.example.Individual_Project.model.Users.Bank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NormalUser extends User{

    private Address address;
    private Bank bank;

    public MyAccount myAccounts;
    public MyProducts myPorducts;
    public ViewProducts viewProducts;

    public NormalUser() {
        super();
    }
    public NormalUser(int userID, String firstname, String lastname, String email, int phoneNumber, Account account) {
        super(userID, firstname, lastname, email, phoneNumber, account);
    }

    public NormalUser(int userID, String firstname, String lastname, String email, int phoneNumber, Account account, Address address, Bank bank, MyAccount myAccounts,
                      MyProducts myProducts, ViewProducts viewProducts ) {
        super(userID, firstname, lastname, email, phoneNumber, account);

        this.bank = bank;
        this.address = address;

        this.myAccounts = myAccounts;
        this.myPorducts = myPorducts;
        this.viewProducts = viewProducts;
    }
}
