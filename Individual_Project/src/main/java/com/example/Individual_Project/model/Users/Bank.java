package com.example.Individual_Project.model.Users;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;
}
