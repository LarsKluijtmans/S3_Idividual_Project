package com.example.individualproject.business.dtoconvertor;

import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.repository.entity.Admin;

public class AdminDTOConvertor {
    private AdminDTOConvertor(){
        throw new IllegalStateException("Utility class");
    }

    public static GetUserDTO convertToDTO(Admin admin) {
       String none = "-None-";
        return GetUserDTO.builder()
                .username(admin.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();
    }
}
