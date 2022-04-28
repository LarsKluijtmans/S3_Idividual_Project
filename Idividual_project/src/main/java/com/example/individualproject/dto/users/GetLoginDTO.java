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
public class GetLoginDTO {

    private String token;
    private String authorization;

    public GetLoginDTO(NormalUser user, String token)
    {
        this.authorization = "NORMAL";
        this.token = token;
    }

    public GetLoginDTO(Admin user, String token)
    {
        this.authorization = "ADMIN";
        this.token = token;
    }
}
