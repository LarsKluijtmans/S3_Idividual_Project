package com.example.Individual_Project.model.Users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String username;
    private String password;

    public String toString()
    {
        return "Username: " + username + ", Passwrod:" + password ;
    }
}
