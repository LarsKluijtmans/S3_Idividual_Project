package com.example.individualproject.controller;

import com.example.individualproject.business.LoginUseCase;
import com.example.individualproject.dto.login.LoginRequestDTO;
import com.example.individualproject.dto.login.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = loginUseCase.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
