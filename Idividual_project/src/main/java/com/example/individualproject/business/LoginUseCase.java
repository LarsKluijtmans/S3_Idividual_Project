package com.example.individualproject.business;

import com.example.individualproject.dto.login.LoginRequestDTO;
import com.example.individualproject.dto.login.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
