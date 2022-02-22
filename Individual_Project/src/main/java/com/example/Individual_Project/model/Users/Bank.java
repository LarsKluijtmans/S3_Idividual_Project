package com.example.Individual_Project.model.Users;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Builder
public class Bank {

    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;

    public Bank(){}

    public Bank(String cardNumber,LocalDate expirationDate,String cvv)
    {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }
}
