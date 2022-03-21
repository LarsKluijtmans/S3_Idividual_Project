package com.example.Individual_Project.model;

import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.model.Users.Address;
import com.example.Individual_Project.model.Users.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NormalUser extends User{

    private Address address;
    private Bank bank;
}
