package com.example.individualproject.business.dtoconvertor;

import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.repository.entity.NormalUser;

public class NormalUserDTOConvertor {
    private NormalUserDTOConvertor(){
        throw new IllegalStateException("Utility class");
    }

    public static GetUserDTO convertToDTO(NormalUser user) {
        return GetUserDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhonenumber())
                .position("NORMAL")
                .build();
    }
}
