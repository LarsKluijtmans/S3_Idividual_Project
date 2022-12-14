package com.example.individualproject.repository.entity;

import com.example.individualproject.dto.users.CreateUserRequestDTO;
import com.example.individualproject.dto.users.UpdateUserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@DiscriminatorValue("NORMAL")
public class NormalUser extends User {

    @Length(min = 1, max = 50)
    @Column(name = "firstname")
    private String firstname;

    @Length(min = 1, max = 50)
    @Column(name = "lastname")
    private String lastname;

    @NotBlank
    @Length(min = 8, max = 50)
    @Column(name = "phonenumber")
    private String phonenumber;

    @NotBlank
    @Length(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "seller")
    private List<Product> productsSelling;

    public NormalUser(CreateUserRequestDTO createDTO) {
        super(createDTO.getUsername(), createDTO.getPassword());

        this.firstname = createDTO.getFirstName();
        this.lastname = createDTO.getLastName();
        this.phonenumber = createDTO.getPhoneNumber();
        this.email = createDTO.getEmail();
    }

    public NormalUser(UpdateUserRequestDTO userRequestDTO, NormalUser user) {
        super(user.getId(), userRequestDTO.getUsername(), user.getPassword());

        this.firstname = userRequestDTO.getFirstName();
        this.lastname = userRequestDTO.getLastName();
        this.phonenumber = userRequestDTO.getPhoneNumber();
        this.email = userRequestDTO.getEmail();
        this.productsSelling = user.getProductsSelling();
    }

    public NormalUser(Long id, String username, String password, String firstname, String lastname, String phoneNumber, String email) {
        super(id, username, password);

        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phoneNumber;
        this.email = email;
        this.productsSelling = new ArrayList<>();
    }

    public NormalUser(Long id, String username, String password, String firstname, String lastname, String phoneNumber, String email, List<Product> productsSelling) {
        super(id, username, password);

        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phoneNumber;
        this.email = email;
        this.productsSelling = productsSelling;
    }

    public NormalUser(String username, String password, String firstname, String lastname, String phoneNumber, String email, List<Product> productsSelling) {
        super(username, password);

        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phoneNumber;
        this.email = email;
        this.productsSelling = productsSelling;
    }

    public NormalUser() {
    }
}
