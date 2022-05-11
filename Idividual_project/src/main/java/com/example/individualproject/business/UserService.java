package com.example.individualproject.business;

import java.util.List;

import com.example.individualproject.dto.users.*;
import org.springframework.stereotype.Service;


public interface UserService {

    List<GetUserDTO> getAllUsers();
    List<GetUserDTO> getAllNormalUsers();
    List<GetUserDTO> getAllAdmins();
    GetUserDTO getUserByID(Long id);
    List<GetUserDTO> getAllUserByName(String name);
    boolean deleteUser(Long id);
    CreateUserResponseDTO addUser(CreateUserRequestDTO createRequestDTO);
    UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateRequestDTO);

    boolean isUsernameUnique (String name);
    boolean isPhoneNumberUnique (String name);
    boolean isEmailUnique (String name);
}
