package com.example.individualproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="position", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 3 ,max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Length(min = 3 ,max = 50)
    @Column(name = "password")
    private String password;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }
}
