package com.example.Individual_Project.repository.Test.NotLogedIn;

import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.Test.DataBase;
import com.example.Individual_Project.repository.DbLogin;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DbNotLogedIn implements DbLogin {

    private DataBase dataBase = new DataBase();

    @Override
    public User GetAccount(String username, String password) {
        for(User user: dataBase.users)
        {
            if(user.getAccount().getUsername() == username && user.getAccount().getPassword() == password)
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean AddAccount(User user) {

        if(user == null)
        {
            return false;
        }
        if(dataBase.users.contains(user))
        {
            return false;
        }

        int count = dataBase.users.size();

        dataBase.users.add(user);

        if(dataBase.users.size() > count)
        {
            return true;
        }
        return false;
    }
}
