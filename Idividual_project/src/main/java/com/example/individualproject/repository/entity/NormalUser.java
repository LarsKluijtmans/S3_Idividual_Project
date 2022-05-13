package com.example.individualproject.repository.entity;

import com.example.individualproject.dto.users.CreateUserRequestDTO;
import com.example.individualproject.dto.users.UpdateUserRequestDTO;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@DiscriminatorValue("NORMAL")
public class NormalUser extends User {

    @Length(min = 1 ,max = 50)
    @Column(name = "firstname")
    private String firstname;

    @Length(min = 1 ,max = 50)
    @Column(name = "lastname")
    private String lastname;

    @NotBlank
    @Length(min = 8 ,max = 50)
    @Column(name = "phonenumber")
    private String phonenumber;

    @NotBlank
    @Length(min = 1 ,max = 50)
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy="seller")
    private List<Product> productsSelling;

    public NormalUser (CreateUserRequestDTO createDTO){
        super(createDTO.getUsername(), createDTO.getPassword());
        this.firstname = createDTO.getFirstName();
        this.lastname = createDTO.getLastName();
        this.phonenumber = createDTO.getPhoneNumber();
        this.email = createDTO.getEmail();
    }
    public NormalUser (UpdateUserRequestDTO userRequestDTO, String username, String password, List<Product> productsSelling){
        super(userRequestDTO.getId(), username, password);
        this.firstname = userRequestDTO.getFirstName();
        this.lastname = userRequestDTO.getLastName();
        this.phonenumber = userRequestDTO.getPhoneNumber();
        this.email = userRequestDTO.getEmail();
        this.productsSelling = productsSelling;
    }
    public NormalUser (Long id, String username, String password, String firstname, String lastname, String phoneNumber, String email){
        super(id,username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phoneNumber;
        this.email = email;
        this.productsSelling = new ArrayList<>();
    }

    public NormalUser (Long id, String username, String password, String firstname, String lastname, String phoneNumber, String email, List<Product> productsSelling){
        super(id,username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phoneNumber;
        this.email = email;
        this.productsSelling = productsSelling;
    }

    public NormalUser (String username, String password, String firstname, String lastname, String phoneNumber, String email, List<Product> productsSelling){
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phoneNumber;
        this.email = email;
        this.productsSelling = productsSelling;
    }

    public NormalUser() {}
}
