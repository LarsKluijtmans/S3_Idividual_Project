package com.example.individualproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="position", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 1 ,max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Length(min = 1 ,max = 50)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Length(min = 1 ,max = 50)
    @Column(name = "position")
    private String position;

    @Length(min = 1 ,max = 50)
    @Column(name = "firstName")
    private String firstName;

    @Length(min = 1 ,max = 50)
    @Column(name = "lastName")
    private String lastName;

    @NotBlank
    @Length(min = 1 ,max = 50)
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @NotBlank
    @Length(min = 1 ,max = 50)
    @Column(name = "email")
    private String email;
}
