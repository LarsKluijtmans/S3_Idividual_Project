package com.example.Individual_Project.business.NormalUser;

import com.example.Individual_Project.model.User;
import com.example.Individual_Project.repository.DbMyAccount;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class MyAccount implements com.example.Individual_Project.business.MyAccount {

    private final DbMyAccount dbMyAccount;

    public MyAccount( DbMyAccount dbMyAccount) {
        this.dbMyAccount = dbMyAccount;
    }

    @Override
    public boolean UpdateAccount(User user) {
        return dbMyAccount.UpdateAccount(user);
    }
}
