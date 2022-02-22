package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.User;

public interface DbLogin {
     boolean AddAccount(User user);
     User GetAccount(String username, String password);
}
