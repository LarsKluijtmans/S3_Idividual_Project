package com.example.individualproject.business.impl;

import com.example.individualproject.business.AccessTokenEncoder;
import com.example.individualproject.business.LoginUseCase;
import com.example.individualproject.business.exception.InvalidCredentialsException;
import com.example.individualproject.dto.login.AccessTokenDTO;
import com.example.individualproject.dto.login.LoginRequestDTO;
import com.example.individualproject.dto.login.LoginResponseDTO;
import com.example.individualproject.repository.AdminRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import com.example.individualproject.repository.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final NormalUserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername());
        NormalUser user = userRepository.findByUsername(loginRequest.getUsername());

        if(admin != null) {
            if (!matchesPassword(loginRequest.getPassword(), admin.getPassword())) {
                throw new InvalidCredentialsException();
            }

            String accessToken = generateAccessToken(admin);
            return LoginResponseDTO.builder().accessToken(accessToken).authorizationLevel("ADMIN").build();
        }

        if (user != null) {
            if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException();
            }

            String accessToken = generateAccessToken(user);
            return LoginResponseDTO.builder().accessToken(accessToken).authorizationLevel("NORMAL").build();
        }

        throw new InvalidCredentialsException();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(User user) {
        Long studentId = user.getId();

        List<String> roles = new ArrayList<>();

        if(user.getClass() == Admin.class){
            roles = List.of("ADMIN");
        }else if(user.getClass() == NormalUser.class){
            roles = List.of("NORMALUSER");
        }

        return accessTokenEncoder.encode(
                AccessTokenDTO.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(studentId)
                        .build());
    }

}
