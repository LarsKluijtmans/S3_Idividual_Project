package com.example.Individual_Project.repository.Test.NormalUser;


import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.Test.DataBase;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class DbMyAccount implements com.example.Individual_Project.repository.DbMyAccount {

    private DataBase dataBase = new DataBase();

    @Override
    public boolean UpdateAccount(User user) {
        for(User u: dataBase.users)
        {
            if(u.getUserID() == u.getUserID())
            {
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
}
