package com.example.Individual_Project.repository.Test.Admin;

import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.Test.DataBase;
import com.example.Individual_Project.repository.DbAllUsers;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class DbAllAccount implements DbAllUsers {

    private final  DataBase dataBase = new DataBase();

    @Override
    public List<User> GetAllAccounts() {
        return dataBase.users;
    }

    @Override
    public List<User> GetAllAccounts(String name) {

        List<User> users = new ArrayList<User>();

        for(User user: dataBase.users) {
            if(user.getFirstname() == name || user.getLastname() == name) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public boolean AddAccount(User user)
    {
        if(user != null) {
            dataBase.users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public User GetAccount(int userID) {

        for(User user: dataBase.users) {
            if(user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean UpdateAccount(User user) {

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
    public boolean DeleteAccount(int userID) {
        for(User user: dataBase.users) {
            if(user.getUserID() == userID) {
                dataBase.users.remove(user);
                return true;
            }
        }

        return false;
    }
}
