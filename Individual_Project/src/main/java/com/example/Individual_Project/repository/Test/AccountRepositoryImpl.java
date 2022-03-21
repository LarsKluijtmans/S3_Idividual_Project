package com.example.Individual_Project.repository.Test;

import com.example.Individual_Project.model.User;
import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.repository.AccountRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private DataBase dataBase = new DataBase();

    @Override
    public List<User> getAllAccounts() {
        return dataBase.users;
    }
    @Override
    public List<User> getAllAccounts(String name) {

        List<User> users = new ArrayList<User>();

        for(User user: dataBase.users) {
            if(user.getFirstname() == name || user.getLastname() == name) {
                users.add(user);
            }
        }
        return users;
    }
    @Override
    public User getAccount(Account account) {
        for(User user: dataBase.users)
        {
            if(user.getAccount().getUsername() == account.getUsername() && user.getAccount().getPassword() == account.getPassword())
            {
                return user;
            }
        }

        return null;
    }
    @Override
    public User getAccount(int userID) {

        for(User user: dataBase.users) {
            if(user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean addAccount(User user) {
        if(user != null) {
            dataBase.users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAccount(User user) {

        for(User u: dataBase.users) {
            if(u.getUserID() == u.getUserID()) {
                u.setFirstname(user.getFirstname());
                u.setLastname(user.getLastname());
                u.setEmail(user.getEmail());
                u.setPhoneNumber(user.getPhoneNumber());
                u.setAccount(user.getAccount());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAccount(int userID) {
        for(User user: dataBase.users) {
            if(user.getUserID() == userID) {
                dataBase.users.remove(user);
                return true;
            }
        }

        return false;
    }
}
