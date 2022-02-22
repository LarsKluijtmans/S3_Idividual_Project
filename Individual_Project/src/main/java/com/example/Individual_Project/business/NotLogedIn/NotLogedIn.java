package com.example.Individual_Project.business.NotLogedIn;

import com.example.Individual_Project.business.Login;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.DbLogin;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class NotLogedIn implements Login {

    private final DbLogin dbLogin;

    public NotLogedIn( DbLogin dbLogin) {
        this.dbLogin = dbLogin;
    }

    @Override
    public User GetAccount(String username, String password) {
        return dbLogin.GetAccount(username, password);
    }

    @Override
    public boolean AddAccount(User user) {
        return dbLogin.AddAccount(user);
    }
}
