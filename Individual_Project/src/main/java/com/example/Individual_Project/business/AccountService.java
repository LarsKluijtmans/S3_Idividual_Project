package com.example.Individual_Project.business;

import com.example.Individual_Project.model.User;
import com.example.Individual_Project.model.Users.Account;

import java.util.List;

public interface AccountService {

     User getAccount(Account account);
     List<User> getAllAccounts();
     List<User> getAllAccounts(String name);
     User getAccount(int userID);

     boolean addAccount(User user);

     boolean updateAccount(User user);

     boolean deleteAccount(int userID);


}
