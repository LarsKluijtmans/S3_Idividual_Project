package com.example.individualproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin(long id, String username, String password) {
        super(id,username, password);
    }
}
