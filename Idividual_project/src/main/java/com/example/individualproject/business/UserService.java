package com.example.individualproject.business;
import java.util.List;

import com.example.individualproject.dto.users.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    //Admin
    List<GetUserDTO> getAllUsers();
    List<GetUserDTO> getAllNormalUsers();
    List<GetUserDTO> getAllAdmins();
    GetUserDTO getUserByID(Long id);
    List<GetUserDTO> getAllUserByName(String name);
    boolean deleteUser(Long id);

    //Normaluser
    GetUserDTO getUser(UserAccountRequestDTO account);
    CreateUserResponseDTO addUser(CreateUserRequestDTO createRequestDTO);
    UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateRequestDTO);

    //all
    boolean isUsernameUnique(String name);
    boolean isPhoneNumberUnique(String phoneNumber);
    boolean isEmailUnique(String email);
}
