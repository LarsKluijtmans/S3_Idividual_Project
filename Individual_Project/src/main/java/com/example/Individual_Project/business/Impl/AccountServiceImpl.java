package com.example.Individual_Project.business.Impl;

import com.example.Individual_Project.business.AccountService;
import com.example.Individual_Project.model.User;

import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final   AccountRepository accountRepository ;

    @Override
    public User getAccount(Account account) {
        return accountRepository.getAccount(account);
    }

    @Override
    public List<User> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public List<User> getAllAccounts(String name) {
        return accountRepository.getAllAccounts(name);
    }

    @Override
    public User getAccount(int userID) {
        return accountRepository.getAccount(userID);
    }

    @Override
    public boolean addAccount(User user) {
        return accountRepository.addAccount(user);
    }

    @Override
    public boolean updateAccount(User user) {
        return accountRepository.updateAccount(user);
    }

    @Override
    public boolean deleteAccount(int userID) {
        return accountRepository.deleteAccount(userID);
    }
}
