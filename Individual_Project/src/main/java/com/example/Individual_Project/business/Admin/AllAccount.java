package com.example.Individual_Project.business.Admin;

import com.example.Individual_Project.business.AllUsers;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.Test.Admin.DbAllAccount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class AllAccount implements AllUsers {

    private final DbAllAccount dbAllAccount;

    public AllAccount( DbAllAccount dbAllAccount) {
        this.dbAllAccount = dbAllAccount;
    }

    @Override
    public List<User> GetAllAccounts() {
        return dbAllAccount.GetAllAccounts();
    }

    @Override
    public List<User> GetAllAccounts(String name) {
        return dbAllAccount.GetAllAccounts(name);
    }

    @Override
    public User GetAccount(int userID) {
        return dbAllAccount.GetAccount(userID);
    }

    @Override
    public boolean AddAccount(User user) {
        return dbAllAccount.AddAccount(user);
    }

    @Override
    public boolean UpdateAccount(User user) {
        return dbAllAccount.UpdateAccount(user);
    }

    @Override
    public boolean DeleteAccount(int userID) {
        return dbAllAccount.DeleteAccount(userID);
    }
}
