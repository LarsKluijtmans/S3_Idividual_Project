package com.example.individualproject.business;

import com.example.individualproject.dto.users.*;

import java.util.List;

public interface UserService {

    List<GetUserDTO> getAllUsers();

    List<GetUserDTO> getAllNormalUsers();

    List<GetUserDTO> getAllAdmins();

    GetUserDTO getUserByName(String username);

    GetUserDTO getUserByNameNormalUser(String username);

    List<GetUserDTO> getAllUserByName(String name);

    boolean deleteUser(String username);

    CreateUserResponseDTO addUser(CreateUserRequestDTO createRequestDTO);

    UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateRequestDTO);

    boolean isUsernameUnique(String name);

    boolean isPhoneNumberUnique(String name);

    boolean isEmailUnique(String name);
}
