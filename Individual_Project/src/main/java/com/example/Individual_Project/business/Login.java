package com.example.Individual_Project.business;

import com.example.Individual_Project.model.User;

public interface Login {

     boolean AddAccount(User user);
     User GetAccount(String username, String password);
}
