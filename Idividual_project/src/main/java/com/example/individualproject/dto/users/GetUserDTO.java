package com.example.individualproject.dto.users;

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
}
