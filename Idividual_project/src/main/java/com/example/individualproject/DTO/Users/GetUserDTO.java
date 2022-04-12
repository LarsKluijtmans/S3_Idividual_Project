package com.example.individualproject.DTO.Users;

import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.User;
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
        this.username = user.getUsername();
        this.firstName = "He's got no name";
        this.lastName = "He's got no last name either";
        this.phoneNumber = "He's got no phone";
        this.email = "He's got no email";
        this.position = "ADMIN";
    }
}
