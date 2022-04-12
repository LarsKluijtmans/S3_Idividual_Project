package com.example.individualproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
    }
}
