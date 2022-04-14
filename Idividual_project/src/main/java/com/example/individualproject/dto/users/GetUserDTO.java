package com.example.individualproject.dto.users;

import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String position;

    public GetUserDTO(NormalUser user)
    {
        this.username = user.getUsername();
        this.firstName = user.getFirstname();
        this.lastName = user.getLastname();
        this.phoneNumber = user.getPhonenumber();
        this.email = user.getEmail();
        this.position = "NORMAL";
    }

    public GetUserDTO(Admin user)
    {
        String none = "-None-";
        this.username = user.getUsername();
        this.firstName = none;
        this.lastName = none;
        this.phoneNumber = none;
        this.email = none;
        this.position = "ADMIN";
    }
}
