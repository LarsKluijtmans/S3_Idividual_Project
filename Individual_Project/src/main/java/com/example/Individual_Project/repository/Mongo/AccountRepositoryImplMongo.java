package com.example.Individual_Project.repository.Mongo;

import com.example.Individual_Project.model.User;
import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImplMongo implements AccountRepository {

    DataBaseConnection database;

    @Override
    public List<User> getAllAccounts() { return null;}

    @Override
    public List<User> getAllAccounts(String name) {
        return null;
    }

    @Override
    public User getAccount(int userID) {
        return null;
    }

    @Override
    public User getAccount(Account account) {
        return null;
    }

    @Override
    public boolean addAccount(User user) {
        return false;
    }

    @Override
    public boolean updateAccount(User user) {
        return false;
    }

    @Override
    public boolean deleteAccount(int userID) {
        return false;
    }
}
