package com.example.Individual_Project.repository;

import com.example.Individual_Project.model.User;

import java.util.List;

public interface AccountRepository {

     List<User> getAllAccounts();
     List<User> getAllAccounts(String name);
     User getAccount(int userID);
     User getAccount(String username, String password);

     boolean addAccount(User user);

     boolean updateAccount(User user);

     boolean deleteAccount(int userID);
}
