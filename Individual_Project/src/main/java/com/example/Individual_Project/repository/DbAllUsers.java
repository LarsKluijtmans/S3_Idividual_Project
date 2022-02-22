package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.User;

import java.util.List;

public interface DbAllUsers {
     List<User> GetAllAccounts();
     List<User> GetAllAccounts(String name);
     User GetAccount(int userID);
     boolean AddAccount(User user);
     boolean UpdateAccount(User user);
     boolean DeleteAccount(int userID);
}
